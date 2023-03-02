package com.sber.filmlibrary.library.Mapper;

import com.sber.filmlibrary.library.dto.RoleDTO;
import com.sber.filmlibrary.library.model.GenericModel;
import com.sber.filmlibrary.library.model.Role;
import com.sber.filmlibrary.library.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper extends GenericMapper<Role, RoleDTO> {

    protected final UserRepository userRepository;

    protected RoleMapper(ModelMapper modelMapper, UserRepository userRepository){
        super(modelMapper, Role.class, RoleDTO.class);
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void setupMapper(){
        modelMapper.createTypeMap(Role.class, RoleDTO.class)
                .addMappings(m -> m.skip(RoleDTO::setUsersIds)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(RoleDTO.class, Role.class)
                .addMappings(m -> m.skip(Role::setUsers)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(RoleDTO source, Role destination) {
        if (!Objects.isNull(source.getUsersIds())){
            destination.setUsers(new HashSet<>(userRepository.findAllById(source.getUsersIds())));
        }
        else {
            destination.setUsers(Collections.emptySet());
        }
    }

    @Override
    protected void mapSpecificFields(Role source, RoleDTO destination) {
        destination.setUsersIds(getIds(source));
    }

    @Override
    protected Set<Long> getIds(Role entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getUsers())
                ? Collections.emptySet()
                : entity.getUsers().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}
