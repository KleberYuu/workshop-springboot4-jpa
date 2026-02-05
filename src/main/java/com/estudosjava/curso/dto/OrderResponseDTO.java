package com.estudosjava.curso.dto;

import com.estudosjava.curso.entities.Order;
import com.estudosjava.curso.entities.OrderItem;
import com.estudosjava.curso.entities.enums.OrderStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderResponseDTO {

    private Long id;
    private Instant moment;
    private OrderStatus orderStatus;

    private UserMinDTO client;

    private List<OrderItemResponseDTO> items = new ArrayList<>();

    private PaymentResponseDTO payment;

    public OrderResponseDTO(Order order) {
        id = order.getId();
        moment = order.getMoment();
        orderStatus = order.getOrderStatus();
        client = new UserMinDTO(order.getClient());

        for (OrderItem item : order.getItems()) {
            items.add(new OrderItemResponseDTO(item));
        }

        if (order.getPayment() != null) {
            this.payment = new PaymentResponseDTO(order.getPayment());
        }
    }

    public Double getTotal(){
        double sum = 0.0;
        for (OrderItemResponseDTO x : items){
            sum += x.getSubTotal();
        }
        return sum;
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

    public List<OrderItemResponseDTO> getItems() {
        return items;
    }

    public PaymentResponseDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentResponseDTO payment) {
        this.payment = payment;
    }
}
