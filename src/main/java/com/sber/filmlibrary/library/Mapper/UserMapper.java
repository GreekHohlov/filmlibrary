package com.sber.filmlibrary.library.Mapper;

import com.sber.filmlibrary.library.dto.UserDTO;
import com.sber.filmlibrary.library.model.GenericModel;
import com.sber.filmlibrary.library.model.Users;
import com.sber.filmlibrary.library.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper extends GenericMapper<Users, UserDTO> {
    protected final OrderRepository orderRepository;
    protected UserMapper(ModelMapper modelMapper, OrderRepository orderRepository){
        super(modelMapper, Users.class, UserDTO.class);
        this.orderRepository = orderRepository;
    }

    @PostConstruct
    public void setupMapper(){
        modelMapper.createTypeMap(Users.class, UserDTO.class)
                .addMappings(m -> m.skip(UserDTO::setOrdersIds)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(UserDTO.class, Users.class)
                .addMappings(m -> m.skip(Users::setOrders)).setPostConverter(toEntityConverter());
    }
    @Override
    protected void mapSpecificFields(UserDTO source, Users destination) {
        if (!Objects.isNull(source.getOrdersIds())){
            destination.setOrders(new HashSet<>(orderRepository.findAllById(source.getOrdersIds())));
        }
        else {
            destination.setOrders(Collections.emptySet());
        }
    }

    @Override
    protected void mapSpecificFields(Users source, UserDTO destination) {
        destination.setOrdersIds(getIds(source));
    }

    @Override
    protected Set<Long> getIds(Users entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getOrders())
                ? Collections.emptySet()
                : entity.getOrders().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}
