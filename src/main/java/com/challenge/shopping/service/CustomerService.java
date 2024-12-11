package com.challenge.shopping.service;
import com.challenge.shopping.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);

    Customer getCustomerById(Long id);

    List<Customer> findAllCustomers();

    void deleteCustomer(Long customerId);

}
