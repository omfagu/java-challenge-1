package com.challenge.shopping.service;

import com.challenge.shopping.entity.Product;
import com.challenge.shopping.repository.ProductRepository;
import com.challenge.shopping.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

 /*   @Test
    public void testGetProductById() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test Product");
        product.setPrice(100.0);
        product.setStock(50);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProductById(productId);

        assertNotNull(foundProduct, "Expected non-null product");
        assertEquals(productId, foundProduct.getId(), "Expected product ID to match");
        assertEquals("Test Product", foundProduct.getName(), "Expected product name to match");
        assertEquals(100.0, foundProduct.getPrice(), "Expected product price to match");
        assertEquals(50, foundProduct.getStock(), "Expected product stock to match");

        System.out.println("Ürün ID: " + foundProduct.getId());
        System.out.println("Ürün Adı: " + foundProduct.getName());
        System.out.println("Ürün Fiyatı: " + foundProduct.getPrice());
        System.out.println("Ürün Stok Miktarı: " + foundProduct.getStock());
    }*/
}
