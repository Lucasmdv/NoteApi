package com.KNBAN.api.application.service.impl;

import com.KNBAN.api.application.dto.request.NoteRequest;
import com.KNBAN.api.application.dto.request.NoteStateRequest;
import com.KNBAN.api.application.dto.response.NoteResponse;
import com.KNBAN.api.application.mapper.NoteMapper;
import com.KNBAN.api.application.service.NoteService;
import com.KNBAN.api.domain.entity.Agenda;
import com.KNBAN.api.domain.entity.Note;
import com.KNBAN.api.domain.entity.Tab;
import com.KNBAN.api.domain.entity.User;
import com.KNBAN.api.domain.exception.ResourceNotFoundException;
import com.KNBAN.api.domain.repository.AgendaRepository;
import com.KNBAN.api.domain.repository.NoteRepository;
import com.KNBAN.api.domain.repository.TabRepository;
import com.KNBAN.api.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final TabRepository tabRepository;
    private final AgendaRepository agendaRepository;
    private final UserRepository userRepository;
    private final NoteMapper noteMapper;

    private Tab getTabIfOwned(String userEmail, Long agendaId, Long tabId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Agenda agenda = agendaRepository.findByIdAndOwner(agendaId, user)
                .orElseThrow(() -> new ResourceNotFoundException("Agenda not found or does not belong to user"));
        return tabRepository.findByIdAndAgenda(tabId, agenda)
                .orElseThrow(() -> new ResourceNotFoundException("Tab not found in this agenda"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteResponse> getAllNotes(String userEmail, Long agendaId, Long tabId) {
        Tab tab = getTabIfOwned(userEmail, agendaId, tabId);
        return noteRepository.findAllByTab(tab).stream()
                .map(noteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public NoteResponse createNote(String userEmail, Long agendaId, Long tabId, NoteRequest request) {
        Tab tab = getTabIfOwned(userEmail, agendaId, tabId);
        Note note = noteMapper.toEntity(request);
        note.setTab(tab);
        Note savedNote = noteRepository.save(note);
        return noteMapper.toResponse(savedNote);
    }

    @Override
    @Transactional(readOnly = true)
    public NoteResponse getNoteById(String userEmail, Long agendaId, Long tabId, Long noteId) {
        Tab tab = getTabIfOwned(userEmail, agendaId, tabId);
        Note note = noteRepository.findByIdAndTab(noteId, tab)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found in this tab"));
        return noteMapper.toResponse(note);
    }

    @Override
    @Transactional
    public NoteResponse updateNote(String userEmail, Long agendaId, Long tabId, Long noteId, NoteRequest request) {
        Tab tab = getTabIfOwned(userEmail, agendaId, tabId);
        Note note = noteRepository.findByIdAndTab(noteId, tab)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found in this tab"));
        
        note.setTitle(request.getTitle());
        note.setBody(request.getBody());
        note.setState(request.getState());
        
        Note updatedNote = noteRepository.save(note);
        return noteMapper.toResponse(updatedNote);
    }

    @Override
    @Transactional
    public NoteResponse updateNoteState(String userEmail, Long agendaId, Long tabId, Long noteId, NoteStateRequest request) {
        Tab tab = getTabIfOwned(userEmail, agendaId, tabId);
        Note note = noteRepository.findByIdAndTab(noteId, tab)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found in this tab"));
        
        note.setState(request.getState());
        
        Note updatedNote = noteRepository.save(note);
        return noteMapper.toResponse(updatedNote);
    }

    @Override
    @Transactional
    public void deleteNote(String userEmail, Long agendaId, Long tabId, Long noteId) {
        Tab tab = getTabIfOwned(userEmail, agendaId, tabId);
        Note note = noteRepository.findByIdAndTab(noteId, tab)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found in this tab"));
        noteRepository.delete(note);
    }
}
