package com.KNBAN.api.infrastructure.web.controller;

import com.KNBAN.api.application.dto.request.TabRequest;
import com.KNBAN.api.application.dto.response.TabResponse;
import com.KNBAN.api.application.service.TabService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendas/{agendaId}/tabs")
@RequiredArgsConstructor
public class TabController {

    private final TabService tabService;

    @GetMapping
    public ResponseEntity<List<TabResponse>> getAllTabs(
            Authentication authentication,
            @PathVariable Long agendaId) {
        return ResponseEntity.ok(tabService.getAllTabs(authentication.getName(), agendaId));
    }

    @PostMapping
    public ResponseEntity<TabResponse> createTab(
            Authentication authentication,
            @PathVariable Long agendaId,
            @Valid @RequestBody TabRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tabService.createTab(authentication.getName(), agendaId, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TabResponse> getTabById(
            Authentication authentication,
            @PathVariable Long agendaId,
            @PathVariable Long id) {
        return ResponseEntity.ok(tabService.getTabById(authentication.getName(), agendaId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TabResponse> updateTab(
            Authentication authentication,
            @PathVariable Long agendaId,
            @PathVariable Long id,
            @Valid @RequestBody TabRequest request) {
        return ResponseEntity.ok(tabService.updateTab(authentication.getName(), agendaId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTab(
            Authentication authentication,
            @PathVariable Long agendaId,
            @PathVariable Long id) {
        tabService.deleteTab(authentication.getName(), agendaId, id);
        return ResponseEntity.noContent().build();
    }
}
