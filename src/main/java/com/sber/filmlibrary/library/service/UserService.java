package com.sber.filmlibrary.library.service;

import com.sber.filmlibrary.library.Mapper.FilmMapper;
import com.sber.filmlibrary.library.Mapper.UserMapper;
import com.sber.filmlibrary.library.dto.FilmDTO;
import com.sber.filmlibrary.library.dto.RoleDTO;
import com.sber.filmlibrary.library.dto.UserDTO;
import com.sber.filmlibrary.library.model.Orders;
import com.sber.filmlibrary.library.model.Users;
import com.sber.filmlibrary.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService extends GenericService<Users, UserDTO> {
    private UserRepository userRepository;
    private final FilmMapper filmMapper;

    protected UserService(UserRepository userRepository, UserMapper userMapper, FilmMapper filmMapper){
        super(userRepository, userMapper);
        this.userRepository = userRepository;
        this.filmMapper = filmMapper;
    }

    @Override
    public UserDTO create(UserDTO object){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        object.setRole(roleDTO);
        return mapper.toDTO(repository.save(mapper.toEntity(object)));
    }

    public Set<FilmDTO> getUserOrdersFilm(Long userId){
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id " + userId + " не найден"));
        Set<Orders> orders = user.getOrders();
        Set<FilmDTO> usersFilm = new HashSet<>();
        for (Orders o: orders) {
            usersFilm.add(filmMapper.toDTO(o.getFilms()));
        }
        return usersFilm;
    }
}
