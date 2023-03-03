package com.sber.filmlibrary.library.service;

import com.sber.filmlibrary.library.Mapper.OrderMapper;
import com.sber.filmlibrary.library.dto.OrderDTO;
import com.sber.filmlibrary.library.model.Films;
import com.sber.filmlibrary.library.model.Orders;
import com.sber.filmlibrary.library.model.Users;
import com.sber.filmlibrary.library.repository.FilmRepository;
import com.sber.filmlibrary.library.repository.OrderRepository;
import com.sber.filmlibrary.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;

@Service
public class OrderService extends GenericService<Orders, OrderDTO> {
    private OrderRepository orderRepository;
    private FilmRepository filmRepository;
    private UserRepository userRepository;
    protected OrderService(OrderRepository orderRepository, OrderMapper orderMapper,
                           UserRepository userRepository, FilmRepository filmRepository){
        super(orderRepository, orderMapper);
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public OrderDTO getFilm(OrderDTO orderDTO) {
        Films film = filmRepository.findById(orderDTO.getFilmId())
                .orElseThrow(() -> new NotFoundException("Фильм с id " + orderDTO.getFilmId() + " не найден"));
        Users user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("Пользователь с id " + orderDTO.getUserId() + " не найден"));
        Orders order = new Orders();
        order.setFilms(film);
        order.setUsers(user);
        if (orderDTO.getPurchase()){
            order.setPurchase(orderDTO.getPurchase());
            order.setRentDate(LocalDateTime.now());
            order.setRentPeriod(0);
        }
        else {
            order.setPurchase(orderDTO.getPurchase());
            order.setRentDate(LocalDateTime.now());
            order.setRentPeriod(orderDTO.getRentPeriod());
        }
        orderRepository.save(order);
        return mapper.toDTO(order);
    }
}
