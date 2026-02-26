package com.estudosjava.curso.dto.user;

import com.estudosjava.curso.dto.RoleResponseDTO;
import com.estudosjava.curso.entities.User;

import java.util.List;

public record UserRoleResponseDTO(

        Long id,
        String name,
        String email,
        String phone,

        List<RoleResponseDTO> role
) {
    public UserRoleResponseDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getRoles().stream()
                        .map(role -> new RoleResponseDTO(
                                role.getAuthority()
                        ))
                        .toList()
        );
    }
}
