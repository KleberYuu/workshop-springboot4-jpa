package com.estudosjava.curso.repositories;

import com.estudosjava.curso.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAuthority(String roleUser);
}
