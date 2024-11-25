package com.challenge.shopping.service;
import com.challenge.shopping.entity.Cart;



public interface CartService {
    Cart getCartByCustomerId(Long customerId);
    Cart updateCart(Long cartId, Cart cartDetails);
    void emptyCart(Long cartId);
    Cart addProductToCart(Long customerId, Long productId, int quantity);

    Cart removeProductFromCart(Long customerId, Long productId);
}
