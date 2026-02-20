package com.estudosjava.curso.dto.orderItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequestDTO (

    @NotNull(message = "Product ID is mandatory")
    Long productId,

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 1, message = "Quantity must be at least 1")
    Integer quantity
){}
