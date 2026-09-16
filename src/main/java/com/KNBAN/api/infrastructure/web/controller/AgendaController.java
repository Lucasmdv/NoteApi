package com.KNBAN.api.infrastructure.web.controller;

import com.KNBAN.api.application.dto.request.AgendaRequest;
import com.KNBAN.api.application.dto.response.AgendaResponse;
import com.KNBAN.api.application.service.AgendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendas")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaService agendaService;

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> getAllAgendas(Authentication authentication) {
        return ResponseEntity.ok(agendaService.getAllAgendas(authentication.getName()));
    }

    @PostMapping
    public ResponseEntity<AgendaResponse> createAgenda(
            Authentication authentication,
            @Valid @RequestBody AgendaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(agendaService.createAgenda(authentication.getName(), request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> getAgendaById(
            Authentication authentication,
            @PathVariable Long id) {
        return ResponseEntity.ok(agendaService.getAgendaById(authentication.getName(), id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaResponse> updateAgenda(
            Authentication authentication,
            @PathVariable Long id,
            @Valid @RequestBody AgendaRequest request) {
        return ResponseEntity.ok(agendaService.updateAgenda(authentication.getName(), id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgenda(
            Authentication authentication,
            @PathVariable Long id) {
        agendaService.deleteAgenda(authentication.getName(), id);
        return ResponseEntity.noContent().build();
    }
}
