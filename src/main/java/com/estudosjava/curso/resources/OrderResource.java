package com.estudosjava.curso.resources;


import com.estudosjava.curso.dto.order.OrderRequestDTO;
import com.estudosjava.curso.dto.order.OrderResponseDTO;
import com.estudosjava.curso.entities.Order;
import com.estudosjava.curso.resources.exceptions.StandardError;
import com.estudosjava.curso.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@Tag(name = "Order Management", description = "APIs for managing orders")
public class OrderResource {

    @Autowired
    private OrderService service;

    @Operation(summary = "Get all orders", description = "Retrieve a list all orders in the system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orders retrieved successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        List<Order> list = service.findAll();
        List<OrderResponseDTO> dtoList = list.stream()
                .map(OrderResponseDTO::new)
                .toList();

        return ResponseEntity.ok().body(dtoList);
    }

    @Operation(summary = "Get orders by ID", description = "Retrieve a order's details using their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order found successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable Long id){
        Order order = service.findById(id);
        return ResponseEntity.ok().body(new OrderResponseDTO(order));
    }

    @Operation(summary = "Create a new order", description = "Add a new order to the system")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Order created successfully"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input - validation errors or business rule violation",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @PostMapping
    public ResponseEntity<OrderResponseDTO> insert(@RequestBody @Valid OrderRequestDTO dto){
        Order order = service.insert(dto);
        OrderResponseDTO response = new OrderResponseDTO(order);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Pay orders by ID", description = "Pay for a system product using your ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment executed successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @PutMapping(value = "/{id}/pay")
    public ResponseEntity<OrderResponseDTO> pay(@PathVariable Long id){
        Order order = service.pay(id);
        return ResponseEntity.ok().body(new OrderResponseDTO(order));
    }

    @Operation(summary = "Cancel orders by ID", description = "Cancel a product from the system using its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cancellation executed successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @PutMapping(value = "/{id}/cancel")
    public ResponseEntity<OrderResponseDTO> cancel(@PathVariable Long id){
        Order order = service.cancel(id);
        return ResponseEntity.ok().body(new OrderResponseDTO(order));
    }

    @Operation(summary = "Ship orders by ID", description = "Ship a product from the system using your ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Shipping executed successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @PutMapping(value = "/{id}/ship")
    public ResponseEntity<OrderResponseDTO> ship(@PathVariable Long id){
        Order order = service.ship(id);
        return ResponseEntity.ok().body(new OrderResponseDTO(order));
    }

    @Operation(summary = "Deliver orders by ID", description = "Deliver a product from the system using your ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Deliver executed successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @PutMapping(value = "/{id}/deliver")
    public ResponseEntity<OrderResponseDTO> deliver(@PathVariable Long id){
        Order order = service.deliver(id);
        return ResponseEntity.ok().body(new OrderResponseDTO(order));
    }
}
