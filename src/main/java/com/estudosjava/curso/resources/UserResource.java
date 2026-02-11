package com.estudosjava.curso.resources;


import com.estudosjava.curso.dto.UserRequestDTO;
import com.estudosjava.curso.dto.UserResponseDTO;
import com.estudosjava.curso.entities.User;
import com.estudosjava.curso.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserResource {

    @Autowired
    private UserService service;

    @Operation(summary = "Get all users", description = "Retrieve a list all users in the system")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> list = service.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Gel user by ID", description = "Retrieve a user's details using their ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserResponseDTO(obj));
    }

    @Operation(summary = "Create a new user", description = "Add a new user to the system")
    @PostMapping
    public ResponseEntity<UserResponseDTO> insert(@RequestBody UserRequestDTO dto){
        User user = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserResponseDTO(user));
    }

    @Operation(summary = "Update a user", description = "Update an existing user's details")
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UserRequestDTO dto){
        User user = service.update(id, dto);
        return ResponseEntity.ok().body(new UserResponseDTO(user));
    }

    @Operation(summary = "Delete a user", description = "Delete a user form system using their ID")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
