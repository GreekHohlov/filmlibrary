package com.sber.filmlibrary.library.service;

import com.sber.filmlibrary.library.Mapper.DirectorMapper;
import com.sber.filmlibrary.library.Mapper.DirectorWithFilmsMapper;
import com.sber.filmlibrary.library.dto.DirectorDTO;
import com.sber.filmlibrary.library.dto.DirectorWithFilmsDTO;
import com.sber.filmlibrary.library.model.Directors;
import com.sber.filmlibrary.library.model.Films;
import com.sber.filmlibrary.library.repository.DirectorRepository;
import com.sber.filmlibrary.library.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class DirectorService extends GenericService<Directors, DirectorDTO> {
    private final DirectorRepository directorRepository;
    private final FilmRepository filmRepository;

    private final DirectorWithFilmsMapper directorWithFilmsMapper;

    protected DirectorService(DirectorRepository directorRepository, DirectorMapper directorMapper,
                              FilmRepository filmRepository, DirectorWithFilmsMapper directorWithFilmsMapper) {
        super(directorRepository, directorMapper);
        this.directorRepository = directorRepository;
        this.filmRepository = filmRepository;
        this.directorWithFilmsMapper = directorWithFilmsMapper;
    }

    public DirectorDTO addFilmToDirector(Long directorId, Long filmId) {
        Directors director = directorRepository.findById(directorId)
                .orElseThrow(() -> new NotFoundException("Режиссер с id " + directorId + " не найден"));
        Films film = filmRepository.findById(filmId)
                .orElseThrow(() -> new NotFoundException("Фильм с id " + filmId + " не найден"));
        director.getFilms().add(film);
        return mapper.toDTO(directorRepository.save(director));
    }

    public List<DirectorWithFilmsDTO> getAllDirectorsWithFilms() {
        return directorWithFilmsMapper.toDTOs(directorRepository.findAll());
    }
}
