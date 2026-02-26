package com.estudosjava.curso.resources;


import com.estudosjava.curso.dto.auth.LoginRequestDTO;
import com.estudosjava.curso.dto.auth.LoginResponseDTO;
import com.estudosjava.curso.dto.auth.RegisterUserRequestDTO;
import com.estudosjava.curso.dto.auth.RegisterUserResponseDTO;
import com.estudosjava.curso.entities.Role;
import com.estudosjava.curso.entities.User;
import com.estudosjava.curso.repositories.RoleRepository;
import com.estudosjava.curso.repositories.UserRepository;
import com.estudosjava.curso.resources.exceptions.StandardError;
import com.estudosjava.curso.security.JWTUtil;
import com.estudosjava.curso.services.exceptions.DuplicateResourceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication and registration endpoints")
public class AuthResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private RoleRepository roleRepository;

    @Operation(
            summary = "Log in",
            description = "Authenticates the user and returns a JWT token"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input - validation errors or business rule violation",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Username does not exist or password is invalid",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody @Valid LoginRequestDTO dto){
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
                );
        String token = jwtUtil.generateToken(dto.email());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(
            summary = "Register new user",
            description = "Creates a new user in the system"
    )
    @ApiResponses(value = {
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
    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDTO> register (@RequestBody @Valid RegisterUserRequestDTO dto){

        if (userRepository.existsByEmail(dto.email())) {
            throw new DuplicateResourceException("Email already registered");
        }

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());
        user.setPassword(passwordEncoder.encode(dto.password()));

        Role roleUser = roleRepository.findByAuthority("ROLE_USER");
        user.getRoles().add(roleUser);

        userRepository.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new RegisterUserResponseDTO(user.getName(), user.getEmail()));
    }
}