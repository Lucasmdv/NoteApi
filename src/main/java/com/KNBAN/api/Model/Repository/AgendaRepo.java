package com.KNBAN.api.Model.Repository;

import com.KNBAN.api.Model.Entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepo extends JpaRepository<Agenda,Long> {
}
