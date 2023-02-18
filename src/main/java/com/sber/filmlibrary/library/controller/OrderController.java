package com.sber.filmlibrary.library.controller;

import com.sber.filmlibrary.library.model.Orders;
import com.sber.filmlibrary.library.repository.FilmRepository;
import com.sber.filmlibrary.library.repository.OrderRepository;
import com.sber.filmlibrary.library.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
@Tag(name = "Заказы", description = "Контроллер для работы с заказами")
public class OrderController extends GenericController<Orders> {

    private final OrderRepository orderRepository;
    private final FilmRepository filmRepository;
    public final UserRepository userRepository;

    public OrderController(OrderRepository orderRepository,
                           FilmRepository filmRepository,
                           UserRepository userRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }
}
