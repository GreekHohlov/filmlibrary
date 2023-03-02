package com.sber.filmlibrary.library.service;

import com.sber.filmlibrary.library.Mapper.RoleMapper;
import com.sber.filmlibrary.library.dto.RoleDTO;
import com.sber.filmlibrary.library.model.Role;
import com.sber.filmlibrary.library.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends GenericService<Role, RoleDTO> {
    protected RoleService(RoleRepository roleRepository, RoleMapper roleMapper){
        super(roleRepository, roleMapper);
    }
}
