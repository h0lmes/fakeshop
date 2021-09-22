package com.h0lmes.fakeshop.repository;

import com.h0lmes.fakeshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
