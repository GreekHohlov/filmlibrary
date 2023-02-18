package com.sber.filmlibrary.library.repository;

import com.sber.filmlibrary.library.model.Films;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository
        extends GenericRepository<Films> {
}
