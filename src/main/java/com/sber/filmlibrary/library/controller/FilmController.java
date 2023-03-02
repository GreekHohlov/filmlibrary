package com.sber.filmlibrary.library.controller;

import com.sber.filmlibrary.library.dto.FilmDTO;
import com.sber.filmlibrary.library.model.Films;
import com.sber.filmlibrary.library.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/films")
@Tag(name = "Фильмы",
        description = "Контроллер для работы с фильмами")
public class FilmController extends GenericController<Films, FilmDTO> {
    private FilmService filmService;
    public FilmController(FilmService filmService) {
        super(filmService);
        this.filmService = filmService;
    }

    @Operation(description = "Добавить режиссера к фильму", method = "addDirector")
    @RequestMapping(value = "/addDirector", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmDTO> addDirector(@RequestParam(value = "filmId") Long filmId,
                                               @RequestParam(value = "directorId") Long directorId) {
        return ResponseEntity.status(HttpStatus.OK).body(filmService.addDirectorToFilm(filmId, directorId));
    }
}
