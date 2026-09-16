package com.KNBAN.api.application.service;

import com.KNBAN.api.application.dto.request.NoteRequest;
import com.KNBAN.api.application.dto.request.NoteStateRequest;
import com.KNBAN.api.application.dto.response.NoteResponse;

import java.util.List;

public interface NoteService {
    List<NoteResponse> getAllNotes(String userEmail, Long agendaId, Long tabId);
    NoteResponse createNote(String userEmail, Long agendaId, Long tabId, NoteRequest request);
    NoteResponse getNoteById(String userEmail, Long agendaId, Long tabId, Long noteId);
    NoteResponse updateNote(String userEmail, Long agendaId, Long tabId, Long noteId, NoteRequest request);
    NoteResponse updateNoteState(String userEmail, Long agendaId, Long tabId, Long noteId, NoteStateRequest request);
    void deleteNote(String userEmail, Long agendaId, Long tabId, Long noteId);
}
