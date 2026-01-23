package com.ahlenius.revent3fx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class DiscoMachine {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long productId;
private Rental rental;
private String productName;
private String description;
private BigDecimal dayPrice;

protected DiscoMachine (){}

    public DiscoMachine(String productName, String description, BigDecimal dayPrice) {
        this.productName = productName;
        this.description = description;
        this.dayPrice = dayPrice;
    }
}
