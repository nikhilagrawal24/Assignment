package com.assignment.repo;

import com.assignment.model.DeviceNote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceNoteRepository extends JpaRepository<DeviceNote, Long> {

    List<DeviceNote> findByDeviceIdOrderByCreatedAtDesc(Long deviceId, Pageable pageable);
}
