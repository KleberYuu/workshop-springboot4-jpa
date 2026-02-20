package com.estudosjava.curso.dto.user;

import com.estudosjava.curso.entities.User;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String phone
) {
    public UserResponseDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }
}
