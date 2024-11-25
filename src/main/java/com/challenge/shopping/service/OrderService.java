package com.challenge.shopping.service;
import com.challenge.shopping.entity.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {
    Order placeOrder(Long customerId);
    Order getOrderForCode(String orderCode);
    List<Order> getAllOrdersForCustomer(Long customerId);

    @Transactional
    Order placeOrderWithProductIds(Long customerId, List<Long> productIds);

    @Transactional
    Order placeOrder(Long customerId, List<Long> productIds);
}
