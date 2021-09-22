package com.h0lmes.fakeshop.web;

import com.h0lmes.fakeshop.model.Cart;
import com.h0lmes.fakeshop.model.Product;
import com.h0lmes.fakeshop.service.CartService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart/")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("{cartId}")
    public ResponseEntity<List<Product>> getCartById(@PathVariable Long cartId) {
        List<Product> cartById = cartService.getCartById(cartId);
        return ResponseEntity.ok(cartById);
    }

    @DeleteMapping("{cartId}")
    @Transactional
    public void deleteCartById(@PathVariable Long cartId) {
        cartService.deleteCartById(cartId);
    }

    @PutMapping("{cartId}")
    @Transactional
    public ResponseEntity<Cart> putProductByCartId(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.putProductByCartId(cartId));
    }
}
