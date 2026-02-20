package com.estudosjava.curso.dto.category;

import com.estudosjava.curso.dto.product.ProductSummaryDTO;
import com.estudosjava.curso.entities.Category;
import java.util.List;

public record CategoryDetailsDTO (
    Long id,
    String name,
    List<ProductSummaryDTO> products
){
    public CategoryDetailsDTO(Category category) {
        this(
                category.getId(),
                category.getName(),
                category.getProducts().stream()
                        .map(product -> new ProductSummaryDTO(
                                product.getId(),
                                product.getName(),
                                product.getPrice()
                        )).toList()
        );
    }
}
