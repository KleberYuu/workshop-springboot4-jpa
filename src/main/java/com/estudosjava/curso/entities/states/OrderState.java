package com.estudosjava.curso.entities.states;

import com.estudosjava.curso.entities.Order;

public interface OrderState {

    void pay(Order order);

    void cancel(Order order);

    void ship(Order order);

    void deliver(Order order);
}
