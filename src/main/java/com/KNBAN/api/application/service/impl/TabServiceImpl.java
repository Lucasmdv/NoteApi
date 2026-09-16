package com.KNBAN.api.application.service.impl;

import com.KNBAN.api.application.dto.request.TabRequest;
import com.KNBAN.api.application.dto.response.TabResponse;
import com.KNBAN.api.application.mapper.TabMapper;
import com.KNBAN.api.application.service.TabService;
import com.KNBAN.api.domain.entity.Agenda;
import com.KNBAN.api.domain.entity.Tab;
import com.KNBAN.api.domain.entity.User;
import com.KNBAN.api.domain.exception.ResourceNotFoundException;
import com.KNBAN.api.domain.repository.AgendaRepository;
import com.KNBAN.api.domain.repository.TabRepository;
import com.KNBAN.api.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TabServiceImpl implements TabService {

    private final TabRepository tabRepository;
    private final AgendaRepository agendaRepository;
    private final UserRepository userRepository;
    private final TabMapper tabMapper;

    private Agenda getAgendaIfOwned(String userEmail, Long agendaId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return agendaRepository.findByIdAndOwner(agendaId, user)
                .orElseThrow(() -> new ResourceNotFoundException("Agenda not found or does not belong to user"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TabResponse> getAllTabs(String userEmail, Long agendaId) {
        Agenda agenda = getAgendaIfOwned(userEmail, agendaId);
        return tabRepository.findAllByAgenda(agenda).stream()
                .map(tabMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TabResponse createTab(String userEmail, Long agendaId, TabRequest request) {
        Agenda agenda = getAgendaIfOwned(userEmail, agendaId);
        Tab tab = tabMapper.toEntity(request);
        tab.setAgenda(agenda);
        Tab savedTab = tabRepository.save(tab);
        return tabMapper.toResponse(savedTab);
    }

    @Override
    @Transactional(readOnly = true)
    public TabResponse getTabById(String userEmail, Long agendaId, Long tabId) {
        Agenda agenda = getAgendaIfOwned(userEmail, agendaId);
        Tab tab = tabRepository.findByIdAndAgenda(tabId, agenda)
                .orElseThrow(() -> new ResourceNotFoundException("Tab not found in this agenda"));
        return tabMapper.toResponse(tab);
    }

    @Override
    @Transactional
    public TabResponse updateTab(String userEmail, Long agendaId, Long tabId, TabRequest request) {
        Agenda agenda = getAgendaIfOwned(userEmail, agendaId);
        Tab tab = tabRepository.findByIdAndAgenda(tabId, agenda)
                .orElseThrow(() -> new ResourceNotFoundException("Tab not found in this agenda"));
        
        tab.setName(request.getName());
        tab.setDescription(request.getDescription());
        
        Tab updatedTab = tabRepository.save(tab);
        return tabMapper.toResponse(updatedTab);
    }

    @Override
    @Transactional
    public void deleteTab(String userEmail, Long agendaId, Long tabId) {
        Agenda agenda = getAgendaIfOwned(userEmail, agendaId);
        Tab tab = tabRepository.findByIdAndAgenda(tabId, agenda)
                .orElseThrow(() -> new ResourceNotFoundException("Tab not found in this agenda"));
        tabRepository.delete(tab);
    }
}
