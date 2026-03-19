package com.assignment.ctrl;

import com.assignment.dto.CreateNoteRequest;
import com.assignment.dto.DeviceNoteResponse;
import com.assignment.service.DeviceNoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/devices")
@Slf4j
public class DeviceNoteController {

    private final DeviceNoteService service;

    public DeviceNoteController(DeviceNoteService service) {
        this.service = service;
    }

    @PostMapping("/{deviceId}/notes")
    public DeviceNoteResponse createNote(
            @PathVariable Long deviceId,
            @RequestHeader(value = "X-User", required = false) String user,
            @RequestBody CreateNoteRequest request
    ) {

        log.info("Create note request: deviceId={}, user={}", deviceId, user);

        return service.createNote(deviceId, request.getNote(), user);
    }

    @GetMapping("/{deviceId}/notes")
    public List<DeviceNoteResponse> getNotes(
            @PathVariable Long deviceId,
            @RequestParam(required = false) Integer limit
    ) {

        return service.getNotes(deviceId, limit);
    }
}
