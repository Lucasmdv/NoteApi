package com.KNBAN.api.application.service;

import com.KNBAN.api.application.dto.request.TabRequest;
import com.KNBAN.api.application.dto.response.TabResponse;

import java.util.List;

public interface TabService {
    List<TabResponse> getAllTabs(String userEmail, Long agendaId);
    TabResponse createTab(String userEmail, Long agendaId, TabRequest request);
    TabResponse getTabById(String userEmail, Long agendaId, Long tabId);
    TabResponse updateTab(String userEmail, Long agendaId, Long tabId, TabRequest request);
    void deleteTab(String userEmail, Long agendaId, Long tabId);
}
