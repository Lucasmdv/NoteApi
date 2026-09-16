package com.KNBAN.api.application.service.impl;

import com.KNBAN.api.application.dto.request.AgendaRequest;
import com.KNBAN.api.application.dto.response.AgendaResponse;
import com.KNBAN.api.application.mapper.AgendaMapper;
import com.KNBAN.api.application.service.AgendaService;
import com.KNBAN.api.domain.entity.Agenda;
import com.KNBAN.api.domain.entity.User;
import com.KNBAN.api.domain.exception.ResourceNotFoundException;
import com.KNBAN.api.domain.repository.AgendaRepository;
import com.KNBAN.api.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgendaServiceImpl implements AgendaService {

    private final AgendaRepository agendaRepository;
    private final UserRepository userRepository;
    private final AgendaMapper agendaMapper;

    private User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgendaResponse> getAllAgendas(String userEmail) {
        User user = getUser(userEmail);
        return agendaRepository.findAllByOwner(user).stream()
                .map(agendaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AgendaResponse createAgenda(String userEmail, AgendaRequest request) {
        User user = getUser(userEmail);
        Agenda agenda = agendaMapper.toEntity(request);
        agenda.setOwner(user);
        Agenda savedAgenda = agendaRepository.save(agenda);
        return agendaMapper.toResponse(savedAgenda);
    }

    @Override
    @Transactional(readOnly = true)
    public AgendaResponse getAgendaById(String userEmail, Long id) {
        User user = getUser(userEmail);
        Agenda agenda = agendaRepository.findByIdAndOwner(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Agenda not found or does not belong to user"));
        return agendaMapper.toResponse(agenda);
    }

    @Override
    @Transactional
    public AgendaResponse updateAgenda(String userEmail, Long id, AgendaRequest request) {
        User user = getUser(userEmail);
        Agenda agenda = agendaRepository.findByIdAndOwner(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Agenda not found or does not belong to user"));
        
        agenda.setName(request.getName());
        agenda.setDescription(request.getDescription());
        
        Agenda updatedAgenda = agendaRepository.save(agenda);
        return agendaMapper.toResponse(updatedAgenda);
    }

    @Override
    @Transactional
    public void deleteAgenda(String userEmail, Long id) {
        User user = getUser(userEmail);
        Agenda agenda = agendaRepository.findByIdAndOwner(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Agenda not found or does not belong to user"));
        agendaRepository.delete(agenda);
    }
}
