package com.KNBAN.api.domain.repository;

import com.KNBAN.api.domain.entity.Note;
import com.KNBAN.api.domain.entity.Tab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByTab(Tab tab);
    Optional<Note> findByIdAndTab(Long id, Tab tab);
}
