package com.h0lmes.fakeshop.repository;

import com.h0lmes.fakeshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
