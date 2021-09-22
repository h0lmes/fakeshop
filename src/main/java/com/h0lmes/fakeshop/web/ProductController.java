package com.h0lmes.fakeshop.web;

import com.h0lmes.fakeshop.model.Product;
import com.h0lmes.fakeshop.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PutMapping("/product/{id}/add")
    public Product putProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.putProductById(id, product);
    }
}
