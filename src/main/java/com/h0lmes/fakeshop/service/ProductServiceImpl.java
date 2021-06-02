package com.h0lmes.fakeshop.service;

import com.h0lmes.fakeshop.model.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private final Map<String, Product> repository = new HashMap<>();

    @Override
    public Product findProductById(String id) {
        return repository.get(id);
    }

    @Override
    public Product putProduct(String id, Product product) {
        product.setId(id);
        repository.put(id, product);
        return findProductById(id);
    }
}