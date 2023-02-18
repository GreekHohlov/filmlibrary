package com.sber.filmlibrary.library.controller;

import com.sber.filmlibrary.library.model.Role;
import com.sber.filmlibrary.library.repository.RoleRepository;
import com.sber.filmlibrary.library.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/role")
@Tag(name = "Роли",
        description = "Контроллер для работы с ролями")
public class RoleController extends GenericController<Role> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleController(RoleRepository roleRepository, UserRepository userRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }
}
