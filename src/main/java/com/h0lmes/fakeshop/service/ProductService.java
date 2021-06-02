package com.h0lmes.fakeshop.service;

import com.h0lmes.fakeshop.model.Product;

public interface ProductService {
    Product findProductById(String id);
    Product putProduct(String id, Product product);
}
