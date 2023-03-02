package com.sber.filmlibrary.library.controller;

import com.sber.filmlibrary.library.dto.RoleDTO;
import com.sber.filmlibrary.library.model.Role;
import com.sber.filmlibrary.library.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/role")
@Tag(name = "Роли",
        description = "Контроллер для работы с ролями")
public class RoleController extends GenericController<Role, RoleDTO> {

    public RoleController(RoleService roleService) {
        super(roleService);
    }
}
