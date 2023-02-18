package com.sber.filmlibrary.library.controller;

import com.sber.filmlibrary.library.model.Films;
import com.sber.filmlibrary.library.repository.DirectorRepository;
import com.sber.filmlibrary.library.repository.FilmRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/films")
@Tag(name = "Фильмы",
        description = "Контроллер для работы с фильмами")
public class FilmController extends GenericController<Films> {
    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;

    public FilmController(FilmRepository filmRepository, DirectorRepository directorRepository) {
        super(filmRepository);
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
    }
}
