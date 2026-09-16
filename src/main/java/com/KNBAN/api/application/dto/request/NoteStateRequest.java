package com.KNBAN.api.application.dto.request;

import com.KNBAN.api.domain.enums.State;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NoteStateRequest {
    @NotNull(message = "State cannot be null")
    private State state;
}
