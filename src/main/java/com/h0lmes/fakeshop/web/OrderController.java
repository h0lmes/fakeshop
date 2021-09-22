package com.h0lmes.fakeshop.web;

import com.h0lmes.fakeshop.service.OrderService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout/{cartId}")
    public void checkout(@PathVariable(required = false) Long cartId) {
        orderService.checkout(cartId);
    }

}
