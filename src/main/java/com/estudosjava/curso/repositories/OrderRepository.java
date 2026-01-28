package com.estudosjava.curso.repositories;

import com.estudosjava.curso.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
