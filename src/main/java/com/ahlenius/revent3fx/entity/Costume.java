package com.ahlenius.revent3fx.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="mascote_costume")
public class Costume {
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

protected Costume(){}

    public Costume(String productName, String description, BigDecimal dayPrice, String season) {
        this.productName = productName;
        this.description = description;
        this.dayPrice = dayPrice;
        this.season = season;
    }
    //SETTERS
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDayPrice(BigDecimal dayPrice) {
        this.dayPrice = dayPrice;
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
        return productName +" Om: "+ description + ". Dagspris: "+ dayPrice +"kr.ex.moms.";  }
}
