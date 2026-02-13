package com.estudosjava.curso.dto;

import com.estudosjava.curso.entities.Category;
import com.estudosjava.curso.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponseDTO {

    private Long id;
    private String name;

    private List<ProductResponseDTO> products = new ArrayList<>();

    public CategoryResponseDTO(Category category) {
        id = category.getId();
        name = category.getName();

        for (Product p : category.getProducts()){
            products.add(new ProductResponseDTO(p));
        }
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

    public List<ProductResponseDTO> getProducts() {
        return products;
    }
}
