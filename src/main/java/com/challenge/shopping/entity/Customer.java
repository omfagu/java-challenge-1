// Customer sınıfı
package com.challenge.shopping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    private String name;
    private String email;


}
