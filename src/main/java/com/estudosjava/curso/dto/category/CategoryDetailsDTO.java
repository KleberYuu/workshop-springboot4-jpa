package com.estudosjava.curso.dto.category;

import com.estudosjava.curso.dto.product.ProductSummaryDTO;
import com.estudosjava.curso.entities.Category;
import com.estudosjava.curso.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailsDTO {
    private Long id;
    private String name;

    private List<ProductSummaryDTO> products = new ArrayList<>();

    public CategoryDetailsDTO(Category category) {
        id = category.getId();
        name = category.getName();

        for (Product p : category.getProducts()){
            products.add(new ProductSummaryDTO(p));
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

    public List<ProductSummaryDTO> getProducts() {
        return products;
    }
}
