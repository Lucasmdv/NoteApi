package com.KNBAN.api.application.mapper;

import com.KNBAN.api.application.dto.request.NoteRequest;
import com.KNBAN.api.application.dto.response.NoteResponse;
import com.KNBAN.api.domain.entity.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    public Note toEntity(NoteRequest request) {
        return Note.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .state(request.getState())
                .build();
    }

    public NoteResponse toResponse(Note entity) {
        return NoteResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .body(entity.getBody())
                .state(entity.getState())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
