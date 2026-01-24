package com.ahlenius.revent3fx.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="disco_machine")
public class DiscoMachine {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="product_id")
private long productId;
@Column(name= "product_name",nullable = false,length = 100, unique = true)
private String productName;
@Column(nullable = false,length = 300)
private String description;
@Column(name= "day_price",nullable = false)
private BigDecimal dayPrice;

protected DiscoMachine (){}

    public DiscoMachine(String productName, String description, BigDecimal dayPrice) {
        this.productName = productName;
        this.description = description;
        this.dayPrice = dayPrice;
    }

    public long getProductId() {
        return productId;
    }
    public BigDecimal getDayPrice() {
        return dayPrice;
    }
    public String getDescription() {
        return description;
    }
    public String getProductName() {
        return productName;
    }
    @Override
    public String toString() {
        return productName +" "+ description + "Dagspris: "+ dayPrice ;   }
}
