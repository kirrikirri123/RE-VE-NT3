package com.ahlenius.revent3fx.pricePolicy;

import java.math.BigDecimal;

public class Employee implements PricePolicy{

    @Override
    public String priceVAT(BigDecimal x) {
        BigDecimal inkVAT = x.multiply(BigDecimal.valueOf(1.25));
        BigDecimal diff = inkVAT.subtract(x);
        return inkVAT + " kr."+"\nInkl. moms 25 % : "+ diff +" kr.";
    }

    @Override
    public BigDecimal discount(BigDecimal x) {
        return x.multiply(BigDecimal.valueOf(0.5));
    }
}
