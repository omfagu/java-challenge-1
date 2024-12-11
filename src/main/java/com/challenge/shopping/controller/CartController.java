package com.challenge.shopping.controller;

import com.challenge.shopping.entity.Cart;
import com.challenge.shopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Cart> getCartByCustomerId(@PathVariable Long customerId) {
        Cart cart = cartService.getCartByCustomerId(customerId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{customerId}/addProduct")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long customerId, @RequestParam Long productId, @RequestParam int quantity) {
        Cart updatedCart = cartService.addProductToCart(customerId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{customerId}/removeProduct")
    public ResponseEntity<Cart> removeProductFromCart(@PathVariable Long customerId, @RequestParam Long productId) {
        Cart updatedCart = cartService.removeProductFromCart(customerId, productId);
        return ResponseEntity.ok(updatedCart);
    }
    @PutMapping("/{customerId}/update")
    public ResponseEntity<Cart> updateCart(
            @PathVariable Long customerId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        Cart updatedCart = cartService.updateCart(customerId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }


    @PostMapping("/{customerId}/empty")
    public ResponseEntity<Void> emptyCart(@PathVariable Long customerId) {
        cartService.emptyCart(customerId);
        return ResponseEntity.ok().build();
    }

}
