package com.estudosjava.curso.dto.orderItem;

public record OrderItemResponseDTO (

    Long productId,
    String productName,
    Integer quantity,
    Double price
){

    public double getSubTotal(){
        return quantity * price;
    }
}
