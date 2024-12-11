package com.challenge.shopping.service.impl;

import com.challenge.shopping.entity.*;
import com.challenge.shopping.repository.CustomerRepository;
import com.challenge.shopping.repository.OrderItemRepository;
import com.challenge.shopping.repository.OrderRepository;
import com.challenge.shopping.repository.ProductRepository;
import com.challenge.shopping.service.CartService;
import com.challenge.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final CartService cartService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductRepository productRepository,
                            OrderItemRepository orderItemRepository,
                            CustomerRepository customerRepository, CartService cartService) {

        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.cartService = cartService;

    }

    @Override
    @Transactional
    public Order placeOrder(Long customerId, List<Long> productIds) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        Cart cart = cartService.getCartByCustomerId(customerId); // Sepeti bir değişkende sakla
        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty for customer ID: " + customerId);
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderCode(UUID.randomUUID().toString());

        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0.0;

        for (CartItem cartItem : cart.getCartItems()) {
            Product product = productRepository.findById(cartItem.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + cartItem.getProduct().getId()));

            if (product.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Not enough stock for product ID: " + product.getId());
            }

            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceAtOrder(product.getPrice());
            orderItems.add(orderItem);

            totalPrice += product.getPrice() * cartItem.getQuantity();
        }

        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);

        cartService.emptyCart(cart.getId());

        return orderRepository.save(order);
    }




    @Override
    public Order placeOrder(Long customerId) {
        return null;
    }

    @Override
    public Order getOrderForCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new RuntimeException("Order not found for code: " + orderCode));
    }

    @Override
    public List<Order> getAllOrdersForCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    @Transactional
    public Order placeOrderWithProductIds(Long customerId, List<Long> productIds) {
        return placeOrder(customerId, productIds);
    }
}