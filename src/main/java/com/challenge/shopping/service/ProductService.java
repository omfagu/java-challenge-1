package com.challenge.shopping.service;


import com.challenge.shopping.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long id);
    Product updateProduct(Long id, Product productDetails);
    void deleteProduct(Long id);

    List<Product> getAllProducts();
}
