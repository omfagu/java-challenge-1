package com.challenge.shopping.service.impl;

import com.challenge.shopping.entity.Cart;
import com.challenge.shopping.entity.CartItem;
import com.challenge.shopping.entity.Product;
import com.challenge.shopping.repository.CartItemRepository;
import com.challenge.shopping.repository.CartRepository;
import com.challenge.shopping.service.CartService;
import com.challenge.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProductService productService, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Cart getCartByCustomerId(Long customerId) {
        return cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Cart not found for customer with ID: " + customerId));
    }

    @Override
    public Cart updateCart(Long cartId, Cart cartDetails) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + cartId));

        cart.setCustomer(cartDetails.getCustomer());
        cart.setCartItems(cartDetails.getCartItems());

        Cart updatedCart = cartRepository.save(cart);
        return updatedCart;
    }

    @Override
    public void emptyCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + cartId));

        cart.getCartItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);
    }

    @Override
    public Cart addProductToCart(Long customerId, Long productId, int quantity) {
        Cart cart = getCartByCustomerId(customerId);
        Product product = productService.getProductById(productId);

        if (product.getStock() < quantity) {
            throw new RuntimeException("Not enough stock for product with ID: " + productId);
        }

        CartItem cartItem = addItem(cart, product, quantity);
        cartItemRepository.save(cartItem);
        product.setStock(product.getStock() - quantity);

        productService.updateProduct(productId, product);
        Cart updatedCart = cartRepository.save(cart);

        return updatedCart;
    }

    private CartItem addItem(Cart cart, Product product, int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);

        cart.getCartItems().add(cartItem);
        cart.setTotalPrice(cart.getTotalPrice() + product.getPrice() * quantity);

        return cartItem;
    }

    @Override
    public Cart removeProductFromCart(Long customerId, Long productId) {
        Cart cart = getCartByCustomerId(customerId);

        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
        } else {
            cart.getCartItems().remove(cartItem);
        }

        cart.setTotalPrice(cart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum());

        return cartRepository.save(cart);
    }}
