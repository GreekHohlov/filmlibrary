package com.sber.filmlibrary.library.Mapper;

import com.sber.filmlibrary.library.dto.DirectorWithFilmsDTO;
import com.sber.filmlibrary.library.model.Directors;
import com.sber.filmlibrary.library.model.GenericModel;
import com.sber.filmlibrary.library.repository.FilmRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DirectorWithFilmsMapper extends GenericMapper<Directors, DirectorWithFilmsDTO> {

    private final FilmRepository filmRepository;

    public DirectorWithFilmsMapper(ModelMapper modelMapper, FilmRepository filmRepository) {
        super(modelMapper, Directors.class, DirectorWithFilmsDTO.class);
        this.filmRepository = filmRepository;
    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(Directors.class, DirectorWithFilmsDTO.class)
                .addMappings(m -> m.skip(DirectorWithFilmsDTO::setFilmsIds)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(DirectorWithFilmsDTO.class, Directors.class)
                .addMappings(m -> m.skip(Directors::setFilms)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(DirectorWithFilmsDTO source, Directors destination) {
        destination.setFilms(new HashSet<>(filmRepository.findAllById(source.getFilmsIds())));
    }

    @Override
    protected void mapSpecificFields(Directors source, DirectorWithFilmsDTO destination) {
        destination.setFilmsIds(getIds(source));
    }

    @Override
    protected Set<Long> getIds(Directors entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getId())
                ? null
                : entity.getFilms().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}
