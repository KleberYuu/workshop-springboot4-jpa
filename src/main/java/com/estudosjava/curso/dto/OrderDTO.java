package com.estudosjava.curso.dto;

import com.estudosjava.curso.entities.enums.OrderStatus;

import java.time.Instant;

public class OrderDTO {

    private Instant moment;
    private OrderStatus orderStatus;
    private Long clientId;

    public OrderDTO() {}

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}