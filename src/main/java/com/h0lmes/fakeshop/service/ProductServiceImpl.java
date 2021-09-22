package com.h0lmes.fakeshop.service;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.h0lmes.fakeshop.model.Product;
import com.h0lmes.fakeshop.repository.ProductRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@XRayEnabled
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository repository) {
        this.productRepository = repository;
    }

    public Product findProductById(Long id) {
        AWSXRay.beginSegment("getProductById");

        Optional<Product> optionalProductById = productRepository.findById(id);
        if (optionalProductById.isEmpty()) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        AWSXRay.endSegment();

        return optionalProductById.get();
    }

    public Product putProductById(Long id, Product product) {
        Optional<Product> optionalProductById = productRepository.findById(id);
        if (optionalProductById.isEmpty()) {
            product.setId(id);
            return productRepository.save(product);
        }

        Product existing = optionalProductById.get();
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());

        return productRepository.save(existing);
    }
}