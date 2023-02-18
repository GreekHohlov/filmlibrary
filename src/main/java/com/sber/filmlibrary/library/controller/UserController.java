package com.sber.filmlibrary.library.controller;

import com.sber.filmlibrary.library.model.Users;
import com.sber.filmlibrary.library.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "Пользователи", description = "Контроллер для работы с пользователями")
public class UserController extends GenericController<Users>{
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }
}
