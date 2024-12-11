package com.challenge.shopping.entity;

import com.challenge.shopping.entity.BaseEntity;
import com.challenge.shopping.entity.Customer;
import com.challenge.shopping.entity.OrderItem;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order extends BaseEntity {

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    private double totalPrice;

    @Column(unique = true)
    private String orderCode;

    public void calculateTotalPrice() {
        double total = 0.0;
        for (OrderItem item : orderItems) {
            total += item.getPriceAtOrder() * item.getQuantity();
        }
        this.totalPrice = total;
    }
}

