package com.KNBAN.api.security.service;

import com.KNBAN.api.application.dto.response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<UserResponse> findById(Long id);

    List<UserResponse> findAll();

    Optional<UserResponse> findByEmail(String email);
}
