package com.assignment.service;

import com.assignment.dto.DeviceNoteResponse;
import com.assignment.model.DeviceNote;
import com.assignment.repo.DeviceNoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class DeviceNoteService {

    private final DeviceNoteRepository repository;

    public DeviceNoteService(DeviceNoteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public DeviceNoteResponse createNote(Long deviceId, String note, String user) {

        if (user == null || user.isBlank()) {
            log.warn("Missing X-User header");
            throw new IllegalArgumentException("X-User header is required");
        }

        if (note == null || note.isBlank()) {
            log.warn("Invalid note: blank");
            throw new IllegalArgumentException("Note cannot be blank");
        }

        if (note.length() > 1000) {
            log.warn("Invalid note: too long");
            throw new IllegalArgumentException("Note must be <= 1000 characters");
        }

        DeviceNote entity = DeviceNote.builder()
                .deviceId(deviceId)
                .note(note)
                .createdBy(user)
                .createdAt(LocalDateTime.now())
                .build();

        DeviceNote saved = repository.save(entity);

        log.info("Note created: id={}, deviceId={}", saved.getId(), deviceId);

        return mapToResponse(saved);
    }

    public List<DeviceNoteResponse> getNotes(Long deviceId, Integer limit) {

        int finalLimit = (limit == null) ? 20 : limit;

        if (finalLimit < 1 || finalLimit > 100) {
            log.warn("Invalid limit: {}", finalLimit);
            throw new IllegalArgumentException("Limit must be between 1 and 100");
        }

        Pageable pageable = PageRequest.of(0, finalLimit);

        return repository.findByDeviceIdOrderByCreatedAtDesc(deviceId, pageable)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private DeviceNoteResponse mapToResponse(DeviceNote entity) {
        return DeviceNoteResponse.builder()
                .id(entity.getId())
                .note(entity.getNote())
                .createdBy(entity.getCreatedBy())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
