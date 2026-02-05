package com.estudosjava.curso.services;

import com.estudosjava.curso.dto.OrderDTO;
import com.estudosjava.curso.dto.OrderItemDTO;
import com.estudosjava.curso.dto.OrderStatusDTO;
import com.estudosjava.curso.entities.Order;
import com.estudosjava.curso.entities.OrderItem;
import com.estudosjava.curso.entities.Product;
import com.estudosjava.curso.entities.User;
import com.estudosjava.curso.entities.enums.OrderStatus;
import com.estudosjava.curso.repositories.OrderItemRepository;
import com.estudosjava.curso.repositories.OrderRepository;
import com.estudosjava.curso.repositories.ProductRepository;
import com.estudosjava.curso.repositories.UserRepository;
import com.estudosjava.curso.services.exceptions.BusinessException;
import com.estudosjava.curso.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    public Order updateStatus(Long id, OrderStatusDTO dto){
        try {
            Order order = repository.getReferenceById(id);
            order.setOrderStatus(dto.getStatus());
            return repository.save(order);

        }  catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
