package com.sber.filmlibrary.library.controller;

import com.sber.filmlibrary.library.dto.DirectorDTO;
import com.sber.filmlibrary.library.model.Directors;
import com.sber.filmlibrary.library.service.DirectorService;
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
@RequestMapping(value = "/directors")
@Tag(name = "Режиссер", description = "Контроллер для работы с режиссерами")
public class DirectorController extends GenericController<Directors, DirectorDTO> {

    private final DirectorService directorService;
    public DirectorController(DirectorService directorService) {
        super(directorService);
        this.directorService = directorService;

    }

    @Operation(description = "Добавить фильм к режиссеру", method = "addFilm")
    @RequestMapping(value = "/addFilm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectorDTO> addFilm(@RequestParam(value = "filmId") Long filmId,
                                             @RequestParam(value = "directorId") Long directorId){
        return ResponseEntity.status(HttpStatus.OK).body(directorService.addFilmToDirector(directorId, filmId));
    }
}
