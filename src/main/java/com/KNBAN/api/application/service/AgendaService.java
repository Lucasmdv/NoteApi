package com.KNBAN.api.application.service;

import com.KNBAN.api.application.dto.request.AgendaRequest;
import com.KNBAN.api.application.dto.response.AgendaResponse;

import java.util.List;

public interface AgendaService {
    List<AgendaResponse> getAllAgendas(String userEmail);
    AgendaResponse createAgenda(String userEmail, AgendaRequest request);
    AgendaResponse getAgendaById(String userEmail, Long id);
    AgendaResponse updateAgenda(String userEmail, Long id, AgendaRequest request);
    void deleteAgenda(String userEmail, Long id);
}
