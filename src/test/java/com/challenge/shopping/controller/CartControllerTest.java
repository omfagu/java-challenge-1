package com.challenge.shopping.controller;

import com.challenge.shopping.entity.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCartByCustomerId() throws Exception {
        Long customerId = 4L;

        mockMvc.perform(get("/api/carts/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customer.id").value(customerId));
    }

    @Test
    public void testAddProductToCart() throws Exception {
        Long customerId = 6L;
        Long productId = 1L;
        int quantity = 3;

        MockHttpServletRequestBuilder request = post("/api/carts/" + customerId + "/addProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .param("productId", productId.toString())
                .param("quantity", String.valueOf(quantity));

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void testRemoveProductFromCart() throws Exception {
        Long customerId = 6L;
        Long productId = 1L;

        mockMvc.perform(delete("/api/carts/" + customerId + "/removeProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("productId", productId.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void testEmptyCart() throws Exception {
        Long customerId = 4L;

        mockMvc.perform(post("/api/carts/" + customerId + "/empty")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
