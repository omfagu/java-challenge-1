package com.challenge.shopping.controller;


import com.challenge.shopping.entity.Customer;
import com.challenge.shopping.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.addCustomer(customer);
        return ResponseEntity.ok(newCustomer);
    }
    @GetMapping
    public ResponseEntity<List<Customer>> findAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        return ResponseEntity.ok(customers);
    }
}
