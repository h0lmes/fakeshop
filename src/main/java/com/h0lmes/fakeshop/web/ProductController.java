package com.h0lmes.fakeshop.web;

import com.h0lmes.fakeshop.model.Product;
import com.h0lmes.fakeshop.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable String id) {
        return productService.findProductById(id);
    }

    @PutMapping("/product/{id}/add")
    public Product putProduct(@PathVariable String id, @RequestBody Product product) {
        return productService.putProduct(id, product);
    }
}
