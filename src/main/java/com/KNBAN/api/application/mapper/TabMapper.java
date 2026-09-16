package com.KNBAN.api.application.mapper;

import com.KNBAN.api.application.dto.request.TabRequest;
import com.KNBAN.api.application.dto.response.TabResponse;
import com.KNBAN.api.domain.entity.Tab;
import org.springframework.stereotype.Component;

@Component
public class TabMapper {

    public Tab toEntity(TabRequest request) {
        return Tab.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public TabResponse toResponse(Tab entity) {
        return TabResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
