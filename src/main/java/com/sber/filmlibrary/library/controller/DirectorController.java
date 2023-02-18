package com.sber.filmlibrary.library.controller;
import com.sber.filmlibrary.library.model.Directors;
import com.sber.filmlibrary.library.repository.DirectorRepository;
import com.sber.filmlibrary.library.repository.FilmRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/directors")
@Tag(name = "Режиссер", description = "Контроллер для работы с режиссерами")
public class DirectorController extends GenericController<Directors> {

    private final DirectorRepository directorRepository;
    private final FilmRepository filmRepository;

    public DirectorController(DirectorRepository directorRepository, FilmRepository filmRepository) {
        super(directorRepository);
        this.directorRepository = directorRepository;
        this.filmRepository = filmRepository;
    }
}
