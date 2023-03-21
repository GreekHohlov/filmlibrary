package com.sber.filmlibrary.library.MVC.controller;

import com.sber.filmlibrary.library.Mapper.DirectorMapper;
import com.sber.filmlibrary.library.dto.DirectorDTO;
import com.sber.filmlibrary.library.dto.FilmDTO;
import com.sber.filmlibrary.library.dto.FilmWithDirectorsDTO;
import com.sber.filmlibrary.library.repository.DirectorRepository;
import com.sber.filmlibrary.library.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("films")
public class MVCFilmController {
    private final FilmService filmService;
    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public MVCFilmController(FilmService filmService, DirectorMapper directorMapper,
                             DirectorRepository directorRepository) {
        this.filmService = filmService;
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
    }

    @GetMapping("")
    public String getAll(Model model) {
        List<FilmWithDirectorsDTO> filmDTOList = filmService.getAllFilmsWithDirectors();
        model.addAttribute("films", filmDTOList);
        return "films/viewAllFilms";
    }

    @GetMapping("/add")
    public String create() {
        return "films/addFilm";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("filmForm") FilmDTO filmDTO) {
        filmService.create(filmDTO);
        return "redirect:/films";
    }

    @GetMapping("/addDirector/{id}")
    public String addDirector(@PathVariable Long id,
                              Model model) {
        List<DirectorDTO> directorsDTOS = directorMapper.toDTOs(directorRepository.findAll());
        FilmDTO filmDTO = filmService.getOne(id);
        model.addAttribute("filmDTO", filmDTO);
        model.addAttribute("director", directorsDTOS);
        return "films/addDirector";
    }

    @PostMapping("/addDirector")
    public String addDirector(@ModelAttribute("id") Long filmId,
                              @ModelAttribute("directorsFio") Long directorId) {
        filmService.addDirectorToFilm(filmId, directorId);
        return "redirect:/films";
    }
}
