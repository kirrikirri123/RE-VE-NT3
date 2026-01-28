package com.ahlenius.revent3fx.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="bouncy_castle")
public class BouncyCastle {
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
@Column(name = "indoor_use")
private boolean indoorUse;

protected BouncyCastle (){}

public BouncyCastle(String productName, String description, BigDecimal dayPrice,boolean indoorUse) {
     this.productName = productName;
     this.description = description;
     this.dayPrice = dayPrice;
     this.indoorUse = indoorUse;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDayPrice(BigDecimal dayPrice) {
        this.dayPrice = dayPrice;
    }
    // SETTERS

    //GETTERS
    public String getProductName() {
        return productName;
    }
    public String getDescription() {
        return description;
    }
    public BigDecimal getDayPrice() {
        return dayPrice;
    }
    public boolean isIndoorUse() {
        return indoorUse;
    }
    public long getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return productName +" Om: "+ description + ". Dagspris: "+ dayPrice +"kr.ex.moms.";   }
}
