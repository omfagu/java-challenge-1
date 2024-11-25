package com.challenge.shopping.service;
import com.challenge.shopping.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    List<Customer> findAllCustomers();
}
