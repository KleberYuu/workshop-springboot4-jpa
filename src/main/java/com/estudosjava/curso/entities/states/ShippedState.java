package com.estudosjava.curso.entities.states;

import com.estudosjava.curso.entities.Order;
import com.estudosjava.curso.entities.enums.OrderStatus;
import com.estudosjava.curso.services.exceptions.BusinessException;

public class ShippedState implements OrderState{
    @Override
    public void pay(Order order) {
        throw new BusinessException("Order already paid");
    }

    @Override
    public void cancel(Order order) {
        throw new BusinessException("Cannot cancel after shipping");
    }

    @Override
    public void ship(Order order) {
        throw new BusinessException("Order already shipping");
    }

    @Override
    public void deliver(Order order) {
        order.setOrderStatus(OrderStatus.DELIVERED);
        order.setState(new DeliveredState());
    }
}
