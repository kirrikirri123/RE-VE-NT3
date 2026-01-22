package com.ahlenius.revent3fx.pricePolicy;

public interface PricePolicy {

    String priceVAT(double x);
    double discount(double x);


}
