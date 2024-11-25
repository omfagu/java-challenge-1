package com.challenge.shopping.service.impl;

import com.challenge.shopping.entity.Customer;
import com.challenge.shopping.entity.Order;
import com.challenge.shopping.entity.OrderItem;
import com.challenge.shopping.entity.Product;
import com.challenge.shopping.repository.CustomerRepository;
import com.challenge.shopping.repository.OrderItemRepository;
import com.challenge.shopping.repository.OrderRepository;
import com.challenge.shopping.repository.ProductRepository;
import com.challenge.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    private final CustomerRepository customerRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Order placeOrder(Long customerId) {
        return null;
    }

    @Override
    public Order getOrderForCode(String orderCode) {
        return null;
    }

    @Override
    public List<Order> getAllOrdersForCustomer(Long customerId) {
        return null;
    }

    @Override
    public Order placeOrderWithProductIds(Long customerId, List<Long> productIds) {
        return null;
    }

    @Override
    @Transactional
    public Order placeOrder(Long customerId, List<Long> productIds) {
        double totalPrice = 0;
        Order newOrder = new Order();

        Customer customer = customerRepository.findById(customerId)  // Müşteriyi bul
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
        newOrder.setCustomer(customer);

        for (Long productId : productIds) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

            if (product.getStock() <= 0) {
                throw new RuntimeException("Product is out of stock: " + productId);
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(newOrder);
            orderItem.setProduct(product);
            orderItem.setQuantity(1);
            orderItem.setPriceAtOrder(product.getPrice());

            orderItemRepository.save(orderItem);

            totalPrice += product.getPrice();

            product.setStock(product.getStock() - 1);
            productRepository.save(product);
        }

        newOrder.setTotalPrice(totalPrice);
        return orderRepository.save(newOrder);
    }
}

