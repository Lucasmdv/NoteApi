package com.KNBAN.api.Model.Entity;

import com.KNBAN.api.Model.Enum.Role;
import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Setter
public class AgendaUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Agenda agenda;

    @ManyToOne(optional = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private Role role;
}
