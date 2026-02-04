package com.estudosjava.curso.dto;

import com.estudosjava.curso.entities.Order;
import com.estudosjava.curso.entities.enums.OrderStatus;

import java.time.Instant;

public class OrderResponseDTO {

    private Long id;
    private Instant moment;
    private OrderStatus orderStatus;

    private UserMinDTO client;

    public OrderResponseDTO(Order order) {
        id = order.getId();
        moment = order.getMoment();
        orderStatus = order.getOrderStatus();
        client = new UserMinDTO(order.getClient());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public UserMinDTO getClient() {
        return client;
    }

    public void setClient(UserMinDTO client) {
        this.client = client;
    }
}
