package com.KNBAN.api.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TabRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String description;
}
