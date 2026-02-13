package com.estudosjava.curso.dto.category;

import com.estudosjava.curso.entities.Category;

public class CategorySummaryDTO {

    private Long id;
    private String name;

    public CategorySummaryDTO(Category category) {
        id = category.getId();
        name = category.getName();
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
}
