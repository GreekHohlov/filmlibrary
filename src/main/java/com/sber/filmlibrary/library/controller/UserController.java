package com.sber.filmlibrary.library.controller;

import com.sber.filmlibrary.library.dto.FilmDTO;
import com.sber.filmlibrary.library.dto.UserDTO;
import com.sber.filmlibrary.library.model.Users;
import com.sber.filmlibrary.library.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "Пользователи", description = "Контроллер для работы с пользователями")
public class UserController extends GenericController<Users, UserDTO>{
    private UserService userService;
    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }
    @Operation(description = "Получить список всех арендованных/купленных фильмов",
            method = "getAllOrdersFilm")
    @RequestMapping(value = "/getAllOrdersFilm", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<FilmDTO>> getAllOrdersFilm(@RequestParam(value = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserOrdersFilm(userId));
    }
}
