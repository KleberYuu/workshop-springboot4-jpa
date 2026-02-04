package com.estudosjava.curso.services;

import com.estudosjava.curso.dto.OrderDTO;
import com.estudosjava.curso.entities.Order;
import com.estudosjava.curso.entities.User;
import com.estudosjava.curso.repositories.OrderRepository;
import com.estudosjava.curso.repositories.UserRepository;
import com.estudosjava.curso.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id){
        Optional<Order> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ResourceNotFoundException(id));
    }

    public Order insert(OrderDTO dto){
        Order order = new Order();
        order.setMoment(dto.getMoment());
        order.setOrderStatus(dto.getOrderStatus());
        Long id = dto.getClientId();
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        order.setClient(user);

        return repository.save(order);
    }
}
