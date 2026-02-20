package com.estudosjava.curso.dto.product;

import com.estudosjava.curso.dto.category.CategorySummaryDTO;
import com.estudosjava.curso.entities.Product;

import java.util.List;

public record ProductResponseDTO (

    Long id,
    String name,
    Double price,
    List<CategorySummaryDTO> categories
){
    public ProductResponseDTO(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategories().stream()
                        .map(category -> new CategorySummaryDTO(
                                category.getId(),
                                category.getName()
                        )).toList()
        );
    }
}