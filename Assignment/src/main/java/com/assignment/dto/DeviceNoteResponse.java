package com.assignment.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DeviceNoteResponse {
    private Long id;
    private String note;
    private String createdBy;
    private LocalDateTime createdAt;
}
