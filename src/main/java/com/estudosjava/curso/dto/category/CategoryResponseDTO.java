package com.estudosjava.curso.dto.category;

import com.estudosjava.curso.entities.Category;

public record CategoryResponseDTO (
    Long id,
    String name
){
    public CategoryResponseDTO (Category category){
        this(
                category.getId(),
                category.getName()
        );
    }
}

