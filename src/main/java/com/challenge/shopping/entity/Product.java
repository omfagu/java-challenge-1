package com.challenge.shopping.entity;

import com.challenge.shopping.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;
    private double price;
    private int stock;
}
