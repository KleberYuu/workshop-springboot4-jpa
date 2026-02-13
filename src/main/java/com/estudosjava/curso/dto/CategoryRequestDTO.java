package com.estudosjava.curso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequestDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 100, message = "The name must be between 3 and 100 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
