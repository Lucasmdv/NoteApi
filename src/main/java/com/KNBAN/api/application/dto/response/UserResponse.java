package com.KNBAN.api.application.dto.response;

import com.KNBAN.api.domain.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private Role role;
}
