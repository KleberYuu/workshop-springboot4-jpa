package com.estudosjava.curso.entities.states;

import com.estudosjava.curso.entities.Order;
import com.estudosjava.curso.services.exceptions.BusinessException;

public class DeliveredState implements OrderState{
    @Override
    public void pay(Order order) {
        throw new BusinessException("Order already paid");
    }

    @Override
    public void cancel(Order order) {
        throw new BusinessException("Cannot cancel after delivered");
    }

    @Override
    public void ship(Order order) {
        throw new BusinessException("Cannot shipping after delivered");
    }

    @Override
    public void deliver(Order order) {
        throw new BusinessException("Order already delivered");
    }
}
