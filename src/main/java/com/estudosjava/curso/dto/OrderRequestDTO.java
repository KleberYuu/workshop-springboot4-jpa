package com.estudosjava.curso.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderRequestDTO {

    private Long clientId;

    private List<OrderItemRequestDTO> items = new ArrayList<>();

    public OrderRequestDTO() {}

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