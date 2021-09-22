package com.h0lmes.fakeshop.model.dto;

import java.util.ArrayList;
import java.util.List;

public class CartDto {

    private Long id;
    private List<ProductDto> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
