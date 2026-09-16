package com.KNBAN.api.application.mapper;

import com.KNBAN.api.application.dto.request.AgendaRequest;
import com.KNBAN.api.application.dto.response.AgendaResponse;
import com.KNBAN.api.domain.entity.Agenda;
import org.springframework.stereotype.Component;

@Component
public class AgendaMapper {

    public Agenda toEntity(AgendaRequest request) {
        return Agenda.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public AgendaResponse toResponse(Agenda entity) {
        return AgendaResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
