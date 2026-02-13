package com.estudosjava.curso.dto.order;

import com.estudosjava.curso.dto.orderItem.OrderItemRequestDTO;
import com.estudosjava.curso.dto.validation.NoDuplicateProducts;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@NoDuplicateProducts
public class OrderRequestDTO {

    @NotNull(message = "Client ID is mandatory")
    @Positive(message = "Client ID must be positive")
    private Long clientId;

    @NotEmpty(message = "Order must have at least one item")
    @Valid
    private List<OrderItemRequestDTO> items = new ArrayList<>();

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<OrderItemRequestDTO> getItems() {
        return items;
    }
}