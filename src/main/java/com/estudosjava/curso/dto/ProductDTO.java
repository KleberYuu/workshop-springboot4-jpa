package com.estudosjava.curso.dto;

import java.util.List;

public class ProductDTO {

    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private List<Long> categoryIds;

    public ProductDTO() {}

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

    public List<Long> getCategoryIds() {
        return categoryIds;
    }
}