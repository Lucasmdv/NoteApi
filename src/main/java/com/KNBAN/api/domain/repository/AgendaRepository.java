package com.KNBAN.api.domain.repository;

import com.KNBAN.api.domain.entity.Agenda;
import com.KNBAN.api.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    List<Agenda> findAllByOwner(User owner);
    Optional<Agenda> findByIdAndOwner(Long id, User owner);
}
