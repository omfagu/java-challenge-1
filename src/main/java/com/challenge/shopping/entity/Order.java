package com.challenge.shopping.entity;

import com.challenge.shopping.entity.BaseEntity;
import com.challenge.shopping.entity.Customer;
import com.challenge.shopping.entity.OrderItem;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order extends BaseEntity {

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    private double totalPrice;

    public void calculateTotalPrice() {
        double total = 0.0;
        for (OrderItem item : orderItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        this.totalPrice = total;
    }

}
