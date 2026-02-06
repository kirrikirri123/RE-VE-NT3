package com.ahlenius.revent3fx.pricePolicy;

import java.math.BigDecimal;

public class Society implements PricePolicy{

    @Override
    public String priceVAT(BigDecimal x) {
        return  String.format("%.2f kr.\nexkl. moms.",x);
    }

    @Override
    public BigDecimal discount(BigDecimal x) {
        return x.multiply(BigDecimal.valueOf(1));
    }
}
