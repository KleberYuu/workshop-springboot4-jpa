package com.estudosjava.curso.services;

import com.estudosjava.curso.dto.OrderDTO;
import com.estudosjava.curso.dto.OrderItemDTO;
import com.estudosjava.curso.entities.*;
import com.estudosjava.curso.entities.enums.OrderStatus;
import com.estudosjava.curso.repositories.OrderItemRepository;
import com.estudosjava.curso.repositories.OrderRepository;
import com.estudosjava.curso.repositories.ProductRepository;
import com.estudosjava.curso.repositories.UserRepository;
import com.estudosjava.curso.services.exceptions.BusinessException;
import com.estudosjava.curso.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id){
        Optional<Order> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ResourceNotFoundException(id));
    }

    public Order insert(OrderDTO dto){

        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new BusinessException("Order must have at least one item");
        }

        User user = userRepository.findById(dto.getClientId()).orElseThrow(() -> new ResourceNotFoundException(dto.getClientId()));

        Order order = new Order();
        order.setClient(user);
        order.setMoment(Instant.now());
        order.setOrderStatus(OrderStatus.WAITING_PAYMENT);

        order = repository.save(order);

        for (OrderItemDTO itemDto : dto.getItems()){

            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(itemDto.getProductId()));

            OrderItem item = new OrderItem(
                    order,
                    product,
                    itemDto.getQuantity(),
                    product.getPrice()
            );

            order.getItems().add(item);

            orderItemRepository.save(item);
        }
        return order;
    }

    public Order pay(Long id){
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        if(order.getItems().isEmpty()){
            throw new BusinessException("Cannot pay an order with no items");
        }

        if (order.getPayment() != null) {
            throw new BusinessException("Order already paid");
        }

        order.pay();
        order.setPayment(new Payment(Instant.now(), order));
        return repository.save(order);
    }

    public Order cancel(Long id){
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        order.cancel();
        return repository.save(order);

    }

    public Order ship(Long id){
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        order.ship();
        return repository.save(order);
    }

    public Order deliver(Long id){
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        order.deliver();
        return repository.save(order);
    }
}
