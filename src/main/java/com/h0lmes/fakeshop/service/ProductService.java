package com.h0lmes.fakeshop.service;

import com.h0lmes.fakeshop.model.Product;

public interface ProductService {
    Product findProductById(Long id);
    Product putProductById(Long id, Product product);
}
