package com.sber.filmlibrary.library.Mapper;

import com.sber.filmlibrary.library.dto.FilmDTO;
import com.sber.filmlibrary.library.model.Films;
import com.sber.filmlibrary.library.model.GenericModel;
import com.sber.filmlibrary.library.repository.DirectorRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmMapper extends GenericMapper<Films, FilmDTO> {
//    private final ModelMapper modelMapper;
    private final DirectorRepository directorRepository;
    protected FilmMapper(ModelMapper modelMapper, DirectorRepository directorRepository){
        super(modelMapper, Films.class, FilmDTO.class);
        this.directorRepository = directorRepository;
    }
    @PostConstruct
    public void setupMapper(){
        modelMapper.createTypeMap(Films.class, FilmDTO.class)
                .addMappings(m -> m.skip(FilmDTO::setDirectorsIds)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(FilmDTO.class, Films.class)
                .addMappings(m -> m.skip(Films::setDirectors)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(FilmDTO source, Films destination) {
        if (!Objects.isNull(source.getDirectorsIds())){
            destination.setDirectors(new HashSet<>(directorRepository.findAllById(source.getDirectorsIds())));
        }
        else {
            destination.setDirectors(Collections.emptySet());
        }
    }

    @Override
    protected void mapSpecificFields(Films source, FilmDTO destination) {
        destination.setDirectorsIds(getIds(source));
    }
    @Override
    protected Set<Long> getIds(Films entity){
        return Objects.isNull(entity) || Objects.isNull(entity.getDirectors())
                ? Collections.emptySet()
                : entity.getDirectors().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}
