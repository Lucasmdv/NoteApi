package com.KNBAN.api.infrastructure.web.controller;

import com.KNBAN.api.application.dto.request.NoteRequest;
import com.KNBAN.api.application.dto.request.NoteStateRequest;
import com.KNBAN.api.application.dto.response.NoteResponse;
import com.KNBAN.api.application.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendas/{agendaId}/tabs/{tabId}/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAllNotes(
            Authentication authentication,
            @PathVariable Long agendaId,
            @PathVariable Long tabId) {
        return ResponseEntity.ok(noteService.getAllNotes(authentication.getName(), agendaId, tabId));
    }

    @PostMapping
    public ResponseEntity<NoteResponse> createNote(
            Authentication authentication,
            @PathVariable Long agendaId,
            @PathVariable Long tabId,
            @Valid @RequestBody NoteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(noteService.createNote(authentication.getName(), agendaId, tabId, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(
            Authentication authentication,
            @PathVariable Long agendaId,
            @PathVariable Long tabId,
            @PathVariable Long id) {
        return ResponseEntity.ok(noteService.getNoteById(authentication.getName(), agendaId, tabId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(
            Authentication authentication,
            @PathVariable Long agendaId,
            @PathVariable Long tabId,
            @PathVariable Long id,
            @Valid @RequestBody NoteRequest request) {
        return ResponseEntity.ok(noteService.updateNote(authentication.getName(), agendaId, tabId, id, request));
    }

    @PatchMapping("/{id}/state")
    public ResponseEntity<NoteResponse> updateNoteState(
            Authentication authentication,
            @PathVariable Long agendaId,
            @PathVariable Long tabId,
            @PathVariable Long id,
            @Valid @RequestBody NoteStateRequest request) {
        return ResponseEntity.ok(noteService.updateNoteState(authentication.getName(), agendaId, tabId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(
            Authentication authentication,
            @PathVariable Long agendaId,
            @PathVariable Long tabId,
            @PathVariable Long id) {
        noteService.deleteNote(authentication.getName(), agendaId, tabId, id);
        return ResponseEntity.noContent().build();
    }
}
