package com.KNBAN.api.application.dto.request;

import com.KNBAN.api.domain.enums.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NoteRequest {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    private String body;
    @NotNull(message = "State cannot be null")
    private State state;
}
