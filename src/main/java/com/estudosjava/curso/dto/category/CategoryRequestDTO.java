package com.estudosjava.curso.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequestDTO (
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 100, message = "The name must be between 3 and 100 characters")
    String name
) {}
