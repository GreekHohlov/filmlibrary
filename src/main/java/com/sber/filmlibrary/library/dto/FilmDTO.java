package com.sber.filmlibrary.library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FilmDTO extends GenericDTO{
    private String title;
    private LocalDate premierYear;
    private String country;
    private String genre;
    private Set<Long> directorsIds;
}
