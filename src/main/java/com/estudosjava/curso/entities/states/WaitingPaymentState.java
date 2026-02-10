package com.estudosjava.curso.entities.states;

import com.estudosjava.curso.entities.Order;
import com.estudosjava.curso.entities.enums.OrderStatus;
import com.estudosjava.curso.services.exceptions.BusinessException;



public class WaitingPaymentState implements OrderState{

    @Override
    public void pay(Order order) {
        order.setOrderStatus(OrderStatus.PAID);
        order.setState(new PaidState());
    }

    @Override
    public void cancel(Order order) {
        order.setOrderStatus(OrderStatus.CANCELED);
        order.setState(new CanceledState());
    }

    @Override
    public void ship(Order order) {
        throw new BusinessException("Cannot ship before payment");
    }

    @Override
    public void deliver(Order order) {
        throw new BusinessException("Cannot deliver before shipping");
    }
}
