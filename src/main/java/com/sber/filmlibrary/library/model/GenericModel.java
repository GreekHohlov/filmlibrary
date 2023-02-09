package com.sber.filmlibrary.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class GenericModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_generator")
    @Column(name = "id", nullable = false)
    private Long id;
//    @Column(name = "created_by", nullable = false)
//    private String createdBy;
//    @Column(name = "created_when", nullable = false)
//    private LocalDate createdWhen;
}