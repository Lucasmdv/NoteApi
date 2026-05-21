package com.KNBAN.api.Model.Repository;

import com.KNBAN.api.Model.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo extends JpaRepository<Note,Long> {
}
