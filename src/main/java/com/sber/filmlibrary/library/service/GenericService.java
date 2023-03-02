package com.sber.filmlibrary.library.service;

import com.sber.filmlibrary.library.Mapper.GenericMapper;
import com.sber.filmlibrary.library.dto.GenericDTO;
import com.sber.filmlibrary.library.model.GenericModel;
import com.sber.filmlibrary.library.repository.GenericRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public abstract class GenericService<T extends GenericModel, N extends GenericDTO> {
    protected final GenericRepository<T> repository;
    protected final GenericMapper<T, N> mapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected GenericService(GenericRepository<T> repository, GenericMapper<T, N> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public N getOne(final Long id) {
        return mapper.toDTO(repository.findById(id).orElseThrow(
                () -> new NotFoundException("Данные по заданному id: " + id + " не найдены")));
    }

    public List<N> listAll() {
        return mapper.toDTOs(repository.findAll());
    }

    public N create(N newObject){
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }

    public N update(N updateObject){
        return mapper.toDTO(repository.save(mapper.toEntity(updateObject)));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
