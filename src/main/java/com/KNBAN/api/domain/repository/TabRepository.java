package com.KNBAN.api.domain.repository;

import com.KNBAN.api.domain.entity.Tab;
import com.KNBAN.api.domain.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TabRepository extends JpaRepository<Tab, Long> {
    List<Tab> findAllByAgenda(Agenda agenda);
    Optional<Tab> findByIdAndAgenda(Long id, Agenda agenda);
}
