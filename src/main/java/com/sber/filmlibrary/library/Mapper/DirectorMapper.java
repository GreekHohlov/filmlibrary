package com.sber.filmlibrary.library.Mapper;

import com.sber.filmlibrary.library.dto.DirectorDTO;
import com.sber.filmlibrary.library.model.Directors;
import com.sber.filmlibrary.library.model.GenericModel;
import com.sber.filmlibrary.library.repository.FilmRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DirectorMapper extends GenericMapper<Directors, DirectorDTO> {
    private final FilmRepository filmRepository;
    protected DirectorMapper(ModelMapper modelMapper, FilmRepository filmRepository){
        super(modelMapper, Directors.class, DirectorDTO.class);
        this.filmRepository = filmRepository;
    }

    @PostConstruct
    public void setupMapper(){
        modelMapper.createTypeMap(Directors.class, DirectorDTO.class)
                .addMappings(m -> m.skip(DirectorDTO::setFilmsIds)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(DirectorDTO.class, Directors.class)
                .addMappings(m -> m.skip(Directors::setFilms)).setPostConverter(toEntityConverter());
    }
    @Override
    protected void mapSpecificFields(DirectorDTO source, Directors destination) {
        if (!Objects.isNull(source.getFilmsIds())){
            destination.setFilms(new HashSet<>(filmRepository.findAllById(source.getFilmsIds())));
        }
        else {
            destination.setFilms(Collections.emptySet());
        }
    }

    @Override
    protected void mapSpecificFields(Directors source, DirectorDTO destination) {
        destination.setFilmsIds(getIds(source));
    }
    @Override
    protected Set<Long> getIds(Directors entity){
        return Objects.isNull(entity) || Objects.isNull(entity.getFilms())
                ? Collections.emptySet()
                : entity.getFilms().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}
