package com.estudosjava.curso.resources;


import com.estudosjava.curso.dto.OrderDTO;
import com.estudosjava.curso.dto.OrderResponseDTO;
import com.estudosjava.curso.dto.OrderStatusDTO;
import com.estudosjava.curso.entities.Order;
import com.estudosjava.curso.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        List<Order> list = service.findAll();
        List<OrderResponseDTO> dtoList = list.stream()
                .map(OrderResponseDTO::new)
                .toList();

        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable Long id){
        Order order = service.findById(id);
        return ResponseEntity.ok().body(new OrderResponseDTO(order));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> insert(@RequestBody OrderDTO dto){
        Order order = service.insert(dto);
        OrderResponseDTO response = new OrderResponseDTO(order);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}/status")
    public ResponseEntity<OrderResponseDTO> updateStatus(@PathVariable Long id, @RequestBody OrderStatusDTO dto){
        Order order = service.updateStatus(id, dto);
        return ResponseEntity.ok().body(new OrderResponseDTO(order));
    }

    @PutMapping(value = "/{id}/pay")
    public ResponseEntity<OrderResponseDTO> pay(@PathVariable Long id){
        Order order = service.pay(id);
        return ResponseEntity.ok().body(new OrderResponseDTO(order));
    }
}
