package com.sber.filmlibrary.library.Mapper;

import com.sber.filmlibrary.library.dto.GenericDTO;
import com.sber.filmlibrary.library.model.GenericModel;

import java.util.List;

public interface Mapper<E extends GenericModel, D extends GenericDTO> {
    E toEntity(D dto);
    D toDTO(E entity);
    List<E> toEntities(List<D> dtos);
    List<D> toDTOs(List<E> entities);
}
