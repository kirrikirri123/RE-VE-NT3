package com.ahlenius.revent3fx.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class BouncyCastle {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long productId;
private Rental rental;// en rental kan ha många bouncy castle eller andra produkter kopplade till rentalid. eller ska jag bara ha en av varje grej?
@Column(nullable = false,length = 100)
private String productName;
private String description;
private BigDecimal dayPrice;
private boolean indoorUse;

protected BouncyCastle (){}

public BouncyCastle(String productName, String description, BigDecimal dayPrice,boolean indoorUse) {
     this.productName = productName;
     this.description = description;
     this.dayPrice = dayPrice;
     this.indoorUse = indoorUse;
    }
}
