package com.challenge.shopping.entity;

import com.challenge.shopping.entity.BaseEntity;
import com.challenge.shopping.entity.Product;
import com.challenge.shopping.entity.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;




@Data
@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private int quantity;
    private double priceAtOrder;


}
