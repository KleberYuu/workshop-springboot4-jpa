package com.estudosjava.curso.resources;


import com.estudosjava.curso.dto.user.UserRequestDTO;
import com.estudosjava.curso.dto.user.UserResponseDTO;
import com.estudosjava.curso.entities.User;
import com.estudosjava.curso.resources.exceptions.StandardError;
import com.estudosjava.curso.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> list = service.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Get user by ID", description = "Retrieve a user's details using their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        User user = service.findById(id);
        return ResponseEntity.ok().body(new UserResponseDTO(user));
    }

    @Operation(summary = "Create a new user", description = "Add a new user to the system")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input - validation errors or business rule violation",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email already exists",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @PostMapping
    public ResponseEntity<UserResponseDTO> insert(@RequestBody @Valid UserRequestDTO dto){
        User user = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserResponseDTO(user));
    }

    @Operation(summary = "Update a user", description = "Update an existing user's details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User update successfully"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input - validation errors or business rule violation",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email already exists",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UserRequestDTO dto){
        User user = service.update(id, dto);
        return ResponseEntity.ok().body(new UserResponseDTO(user));
    }

    @Operation(summary = "Delete a user", description = "Delete a user form system using their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Integrity violation - user cannot be deleted",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
