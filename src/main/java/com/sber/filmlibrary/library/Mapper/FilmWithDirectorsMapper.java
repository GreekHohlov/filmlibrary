package com.sber.filmlibrary.library.Mapper;

import com.sber.filmlibrary.library.dto.FilmWithDirectorsDTO;
import com.sber.filmlibrary.library.model.Films;
import com.sber.filmlibrary.library.model.GenericModel;
import com.sber.filmlibrary.library.repository.DirectorRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmWithDirectorsMapper extends GenericMapper<Films, FilmWithDirectorsDTO> {
    private final DirectorRepository directorRepository;
    protected FilmWithDirectorsMapper(ModelMapper modelMapper,
                                      DirectorRepository directorRepository) {
        super(modelMapper, Films.class, FilmWithDirectorsDTO.class);
        this.directorRepository = directorRepository;
    }
    @PostConstruct
    protected void setupMapper(){
        modelMapper.createTypeMap(Films.class, FilmWithDirectorsDTO.class)
                .addMappings(m -> m.skip(FilmWithDirectorsDTO::setDirectorsIds)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(FilmWithDirectorsDTO.class, Films.class)
                .addMappings(m -> m.skip(Films::setDirectors)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(FilmWithDirectorsDTO source, Films destination) {
        destination.setDirectors(new HashSet<>(directorRepository.findAllById(source.getDirectorsIds())));
    }

    @Override
    protected void mapSpecificFields(Films source, FilmWithDirectorsDTO destination) {
        destination.setDirectorsIds(getIds(source));
    }

    @Override
    protected Set<Long> getIds(Films entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getId())
                ? null
                : entity.getDirectors().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}
