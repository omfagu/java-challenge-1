package com.challenge.shopping.controller;

import com.challenge.shopping.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetProductById() throws Exception {
        Long productId = 1L;

        mockMvc.perform(get("/api/products/" + productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productId));
    }

    @Test
    public void testCreateProduct() throws Exception {

        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);
        product.setStock(50);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(product.getName()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andExpect(jsonPath("$.stock").value(product.getStock()));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Long productId = 1L;

        Product updatedProduct = new Product();
        updatedProduct.setName("Güncellenmiş Ürün");
        updatedProduct.setPrice(200.0);
        updatedProduct.setStock(100);

        mockMvc.perform(put("/api/products/" + productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updatedProduct.getName()))
                .andExpect(jsonPath("$.price").value(updatedProduct.getPrice()))
                .andExpect(jsonPath("$.stock").value(updatedProduct.getStock()));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Long productId = 2L;

        mockMvc.perform(delete("/api/products/" + productId))
                .andExpect(status().isOk());
    }
}
