package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.security.RoleDTO;
import com.example.bookreservation.entity.security.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements AbstractMapper<Role, RoleDTO> {

    @Override
    public RoleDTO toDTO(Role role) {
        return mapper.map(role, RoleDTO.class);
    }

    @Override
    public Role toEntity(RoleDTO roleDTO) {
        return mapper.map(roleDTO, Role.class);
    }
}
