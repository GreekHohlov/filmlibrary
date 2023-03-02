package com.sber.filmlibrary.library.service;

import com.sber.filmlibrary.library.Mapper.FilmMapper;
import com.sber.filmlibrary.library.dto.FilmDTO;
import com.sber.filmlibrary.library.model.Directors;
import com.sber.filmlibrary.library.model.Films;
import com.sber.filmlibrary.library.repository.DirectorRepository;
import com.sber.filmlibrary.library.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class FilmService extends GenericService<Films, FilmDTO> {
    private FilmRepository filmRepository;
    private DirectorRepository directorRepository;
    protected FilmService(FilmRepository filmRepository, FilmMapper filmMapper,
                          DirectorRepository directorRepository) {
        super(filmRepository, filmMapper);
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
    }

    public FilmDTO addDirectorToFilm(Long filmId, Long directorId){
        Films film = filmRepository.findById(filmId)
                .orElseThrow(() -> new NotFoundException("Фильм с id " + filmId + " не найден"));
        Directors director = directorRepository.findById(directorId)
                .orElseThrow(() -> new NotFoundException("Режиссер с id " + directorId + " не найден"));
        film.getDirectors().add(director);
        return mapper.toDTO(filmRepository.save(film));
    }

}
