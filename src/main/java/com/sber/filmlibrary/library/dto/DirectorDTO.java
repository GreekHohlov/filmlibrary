package com.sber.filmlibrary.library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DirectorDTO extends GenericDTO{
    private String directorsFio;
    private Integer position;
    private Set<Long> filmsIds;
}
