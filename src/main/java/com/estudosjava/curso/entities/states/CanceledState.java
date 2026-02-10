package com.estudosjava.curso.entities.states;

import com.estudosjava.curso.entities.Order;
import com.estudosjava.curso.services.exceptions.BusinessException;

public class CanceledState implements OrderState{
    @Override
    public void pay(Order order) {
        throw new BusinessException("Cannot paid after canceled");
    }

    @Override
    public void cancel(Order order) {
        throw new BusinessException("Order already canceled");
    }

    @Override
    public void ship(Order order) {
        throw new BusinessException("Cannot ship after canceled");
    }

    @Override
    public void deliver(Order order) {
        throw new BusinessException("Cannot deliver after canceled");
    }
}
