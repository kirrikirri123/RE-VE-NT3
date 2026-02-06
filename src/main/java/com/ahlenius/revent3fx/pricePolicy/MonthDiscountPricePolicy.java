package com.ahlenius.revent3fx.pricePolicy;

import java.math.BigDecimal;

public class MonthDiscountPricePolicy {

    public MonthDiscountPricePolicy(){}

    public BigDecimal calculateDay(BigDecimal dayPrice, int days) {
        BigDecimal price = dayPrice.multiply(BigDecimal.valueOf(days));
        if (days >= 30) {
            price = price.multiply(BigDecimal.valueOf(0.7));
        }
        return price;
    }
}
