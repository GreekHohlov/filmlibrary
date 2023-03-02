package com.sber.filmlibrary.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "role_seq", allocationSize = 1)
public class Role extends GenericModel {
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "role")
    private Set<Users> users;
}
