package com.sber.filmlibrary.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "directors")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "directors_seq", allocationSize = 1)
public class Directors
        extends GenericModel {
    @Column(name = "directors_fio", nullable = false)
    private String directorsFio;

    @Column(name = "position")
    private Integer position;

    @ManyToMany(mappedBy = "directors")
    private Set<Films> films;
}
