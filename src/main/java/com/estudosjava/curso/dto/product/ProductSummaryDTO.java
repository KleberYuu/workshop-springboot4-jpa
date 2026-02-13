package com.estudosjava.curso.dto.product;

import com.estudosjava.curso.entities.Product;

public class ProductSummaryDTO {

    private Long id;
    private String name;
    private Double price;

    public ProductSummaryDTO(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
