package com.challenge.shopping.controller;


import com.challenge.shopping.entity.Cart;
import com.challenge.shopping.entity.Order;
import com.challenge.shopping.service.CartService;
import com.challenge.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;

    @Autowired
    public OrderController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }


    @PostMapping("/{customerId}/products")
    public ResponseEntity<Order> placeOrderWithProductIds(@PathVariable Long customerId, @RequestBody List<Long> productIds) {
        Order order = orderService.placeOrder(customerId, productIds);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{orderCode}")
    public ResponseEntity<Order> getOrderForCode(@PathVariable String orderCode) {
        Order order = orderService.getOrderForCode(orderCode);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getAllOrdersForCustomer(@PathVariable Long customerId) {
        List<Order> orders = orderService.getAllOrdersForCustomer(customerId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/{customerId}/cart")
    public ResponseEntity<Order> placeOrderWithCart(@PathVariable Long customerId) {
        Cart cart = cartService.getCartByCustomerId(customerId);
        List<Long> productIds = cart.getCartItems().stream()
                .map(cartItem -> cartItem.getProduct().getId())
                .collect(Collectors.toList());

        // Sipariş oluştur
        Order order = orderService.placeOrder(customerId, productIds);

        // Sepeti boşalt
        cartService.emptyCart(cart.getId());

        return ResponseEntity.ok(order);
    }
}
