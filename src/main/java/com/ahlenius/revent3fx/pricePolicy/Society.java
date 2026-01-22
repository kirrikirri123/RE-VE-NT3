package com.ahlenius.revent3fx.pricePolicy;

public class Society implements PricePolicy{

    @Override
    public String priceVAT(double x) {
        return String.format("%.2f kr.\nexkl. moms.",x);
    }

    @Override
    public double discount(double x) {
        return x * 1;
    }
}
