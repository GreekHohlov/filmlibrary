package com.sber.filmlibrary.library.Mapper;

import com.sber.filmlibrary.library.dto.OrderDTO;
import com.sber.filmlibrary.library.model.Orders;
import com.sber.filmlibrary.library.repository.FilmRepository;
import com.sber.filmlibrary.library.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.Set;
@Component
public class OrderMapper extends GenericMapper<Orders, OrderDTO> {

    private final UserRepository userRepository;
    private final FilmRepository filmRepository;
    protected OrderMapper(ModelMapper modelMapper,
                          UserRepository userRepository,
                          FilmRepository filmRepository){
        super(modelMapper, Orders.class, OrderDTO.class);
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
    }

    @PostConstruct
    public void setupMapper(){
        modelMapper.createTypeMap(Orders.class, OrderDTO.class)
                .addMappings(m -> m.skip(OrderDTO::setFilmId)).setPostConverter(toDTOConverter())
                .addMappings(m -> m.skip(OrderDTO::setUserId)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(OrderDTO.class, Orders.class)
                .addMappings(m -> m.skip(Orders::setFilms)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Orders::setUsers)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(OrderDTO source, Orders destination) {
        destination.setUsers(userRepository.findById(source.getUserId()).orElseThrow(() -> new NotFoundException("Пользователь не найден")));
        destination.setFilms(filmRepository.findById(source.getFilmId()).orElseThrow(() -> new NotFoundException("Фильм не найден")));
    }

    @Override
    protected void mapSpecificFields(Orders source, OrderDTO destination) {
        destination.setUserId(source.getUsers().getId());
        destination.setFilmId(source.getFilms().getId());
    }

    @Override
    protected Set<Long> getIds(Orders entity) {
        throw new UnsupportedOperationException("Метод недоступен");
    }
}
