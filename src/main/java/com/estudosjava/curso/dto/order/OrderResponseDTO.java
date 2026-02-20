package com.estudosjava.curso.dto.order;

import com.estudosjava.curso.dto.orderItem.OrderItemResponseDTO;
import com.estudosjava.curso.dto.PaymentResponseDTO;
import com.estudosjava.curso.dto.user.UserResponseDTO;
import com.estudosjava.curso.entities.Order;
import com.estudosjava.curso.entities.enums.OrderStatus;

import java.time.Instant;
import java.util.List;

public record OrderResponseDTO (

        Long id,
        Instant moment,
        OrderStatus orderStatus,

        UserResponseDTO client,

        List<OrderItemResponseDTO> items,

        PaymentResponseDTO payment

){
    public OrderResponseDTO(Order order) {
        this(
                order.getId(),
                order.getMoment(),
                order.getOrderStatus(),
                new UserResponseDTO(
                        order.getClient().getId(),
                        order.getClient().getName(),
                        order.getClient().getEmail(),
                        order.getClient().getPhone()
                ),
                order.getItems().stream()
                        .map(item -> new OrderItemResponseDTO(
                                item.getProduct().getId(),
                                item.getProduct().getName(),
                                item.getQuantity(),
                                item.getPrice()
                        ))
                        .toList(),
                order.getPayment() != null
                        ? new PaymentResponseDTO(order.getPayment().getMoment())
                        : null
        );
    }

    public Double getTotal() {
        return items.stream()
                .mapToDouble(OrderItemResponseDTO::getSubTotal)
                .sum();
    }
}
