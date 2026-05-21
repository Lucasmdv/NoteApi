package com.KNBAN.api.Model.Entity;

import jakarta.persistence.*;

import java.security.Permission;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"note_id", "user_id"})
)
public class NoteShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Note note;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private Permission permission;
}
