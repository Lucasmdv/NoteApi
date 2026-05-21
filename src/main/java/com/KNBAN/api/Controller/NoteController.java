package com.KNBAN.api.Controller;

import com.KNBAN.api.Model.Entity.Note;
import com.KNBAN.api.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/new")
    public ResponseEntity<Note> note(Note note) {
        return ResponseEntity.ok(note);
    }
}
