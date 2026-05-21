package com.KNBAN.api.security.service;

import com.KNBAN.api.security.dto.UserResponse;
import com.KNBAN.api.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .roles(user.getRoles())
                        .build())
                .collect(Collectors.toList());
    }

    public UserResponse findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .roles(user.getRoles())
                        .build())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
