package com.sber.filmlibrary.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "films")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "films_seq", allocationSize = 1)
public class Films
        extends GenericModel {

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "premier_year", nullable = false)
    private LocalDate premierYear;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "genre", nullable = false)
    private String genre;

    @ManyToMany
    @JoinTable(name = "film_directors",
            joinColumns = @JoinColumn(name = "film_id"), foreignKey = @ForeignKey(name = "FK_FILMS_DIRECTORS"),
            inverseJoinColumns = @JoinColumn(name = "director_id"), inverseForeignKey = @ForeignKey(name = "FK_DIRECTORS_FILMS"))
    private Set<Directors> directors;
    @OneToMany(mappedBy = "films")
    private Set<Orders> orders;

}
