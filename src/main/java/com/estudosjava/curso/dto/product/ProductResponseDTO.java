package com.estudosjava.curso.dto.product;

import com.estudosjava.curso.dto.category.CategorySummaryDTO;
import com.estudosjava.curso.entities.Category;
import com.estudosjava.curso.entities.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductResponseDTO {

    private Long id;
    private String name;
    private Double price;
    private Set<CategorySummaryDTO> categories = new HashSet<>();

    public ProductResponseDTO(Product product){
        id = product.getId();
        name = product.getName();
        price = product.getPrice();

        for (Category c : product.getCategories()){
            categories.add(new CategorySummaryDTO(c));
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<CategorySummaryDTO> getCategories() {
        return categories;
    }
}
