package com.KNBAN.api.Model.Entity;

import com.KNBAN.api.Model.Enum.State;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private State state;

    @ManyToOne(optional = false)
    private Agenda agenda;
}
