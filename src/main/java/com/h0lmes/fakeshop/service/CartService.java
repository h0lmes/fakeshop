package com.h0lmes.fakeshop.service;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.h0lmes.fakeshop.model.Cart;
import com.h0lmes.fakeshop.model.Product;
import com.h0lmes.fakeshop.repository.CartRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@XRayEnabled
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Transactional
    public List<Product> getCartById(Long cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            return new ArrayList<>(optionalCart.get().getProducts());
        }

        Cart cart = new Cart();
        return cartRepository.save(cart).getProducts();
    }

    public void deleteCartById(Long cartId) {
        cartRepository.findById(cartId).ifPresent(cartRepository::delete);
    }

    @Transactional
    public Cart putProductByCartId(Long cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);

        if (optionalCart.isEmpty()) {
            throw new RuntimeException("Cart not found");
        }

        Product product = new Product();
        product.setName("Name ".concat(String.valueOf(ThreadLocalRandom.current().nextInt(100))));
        product.setPrice(new BigDecimal(ThreadLocalRandom.current().nextInt(1001)));

        optionalCart.ifPresent(cart1 -> cart1.getProducts().add(product));

        return optionalCart.get();
    }
}
