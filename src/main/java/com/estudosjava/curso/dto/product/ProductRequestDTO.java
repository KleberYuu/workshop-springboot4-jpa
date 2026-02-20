package com.estudosjava.curso.dto.product;

import com.estudosjava.curso.dto.validation.UniqueList;
import jakarta.validation.constraints.*;
import java.util.List;

public record ProductRequestDTO (
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    String name,

    String description,

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be greater than zero")
    Double price,

    String imgUrl,

    @NotEmpty(message = "At least one category is required")
    @UniqueList
    List<Long> categoryIds
){}