package com.ahlenius.revent3fx.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="mascote_costume")
public class MascoteCostume {
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
@Column(length = 10)
private String season;

protected MascoteCostume(){}

    public MascoteCostume(String productName, String description, BigDecimal dayPrice,String season) {
        this.productName = productName;
        this.description = description;
        this.dayPrice = dayPrice;
        this.season = season;
    }

    // GETTERS
    public long getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public String getDescription() {
        return description;
    }
    public BigDecimal getDayPrice() {
        return dayPrice;
    }
    public String getSeason() {
        return season;
    }

    @Override
    public String toString() {
        return productName +" "+ description + "Dagspris: "+ dayPrice ;   }
}
