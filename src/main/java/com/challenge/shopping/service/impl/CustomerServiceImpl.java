package com.challenge.shopping.service.impl;

import com.challenge.shopping.entity.Cart;
import com.challenge.shopping.entity.Customer;
import com.challenge.shopping.repository.CartRepository;
import com.challenge.shopping.repository.CustomerRepository;
import com.challenge.shopping.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    public Customer addCustomer(Customer customer) {
        customer = customerRepository.save(customer);
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cartRepository.save(cart);


        return customer;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
}
