package com.estudosjava.curso.dto.product;

import com.estudosjava.curso.dto.category.CategorySummaryDTO;
import com.estudosjava.curso.entities.Category;
import com.estudosjava.curso.entities.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductDetailsDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private Set<CategorySummaryDTO> categories = new HashSet<>();

    public ProductDetailsDTO(Product product){
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<CategorySummaryDTO> getCategories() {
        return categories;
    }
}
