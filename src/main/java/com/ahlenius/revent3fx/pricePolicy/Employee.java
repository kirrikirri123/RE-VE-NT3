package com.ahlenius.revent3fx.pricePolicy;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Employee implements PricePolicy{
    private DecimalFormat deciForm = new DecimalFormat("0.00");

    @Override
    public String priceVAT(BigDecimal x) {
        BigDecimal inkVAT = x.multiply(BigDecimal.valueOf(1.25));
        BigDecimal diff = inkVAT.subtract(x);
        return deciForm.format(inkVAT) + " kr."+" - Var av moms 25 % = "+ deciForm.format(diff) +" kr.";
    }

    @Override
    public BigDecimal discount(BigDecimal x) {
        return x.multiply(BigDecimal.valueOf(0.5));
    }
}
