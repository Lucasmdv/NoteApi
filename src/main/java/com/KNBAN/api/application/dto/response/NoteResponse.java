package com.KNBAN.api.application.dto.response;

import com.KNBAN.api.domain.enums.State;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NoteResponse {
    private Long id;
    private String title;
    private String body;
    private State state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
