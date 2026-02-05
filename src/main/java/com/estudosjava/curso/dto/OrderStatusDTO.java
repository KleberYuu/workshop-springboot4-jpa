package com.estudosjava.curso.dto;

import com.estudosjava.curso.entities.enums.OrderStatus;

public class OrderStatusDTO {

    private OrderStatus status;

    public OrderStatusDTO() {}

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
