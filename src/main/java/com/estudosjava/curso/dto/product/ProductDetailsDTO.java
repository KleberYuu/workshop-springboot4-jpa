package com.estudosjava.curso.dto.product;

import com.estudosjava.curso.dto.category.CategorySummaryDTO;
import com.estudosjava.curso.entities.Product;

import java.util.List;

public record ProductDetailsDTO (

    Long id,
    String name,
    String description,
    Double price,
    String imgUrl,
    List<CategorySummaryDTO> categories

){
    public ProductDetailsDTO(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImgUrl(),
                product.getCategories().stream()
                        .map(category -> new CategorySummaryDTO(
                                category.getId(),
                                category.getName()
                        )).toList()
        );
    }
}


