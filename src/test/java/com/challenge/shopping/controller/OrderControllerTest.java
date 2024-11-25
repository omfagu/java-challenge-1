package com.challenge.shopping.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPlaceOrderWithProductIds() throws Exception {
        Long customerId = 11L;
        String productIds = Arrays.asList(1L, 5L, 3L).toString();

        mockMvc.perform(post("/api/orders/" + customerId + "/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productIds))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOrderForCode() throws Exception {
        String orderCode = "orderCode";

        mockMvc.perform(get("/api/orders/" + orderCode)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllOrdersForCustomer() throws Exception {
        Long customerId = 6L;

        mockMvc.perform(get("/api/orders/customer/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
