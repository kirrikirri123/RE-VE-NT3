package com.ahlenius.revent3fx.pricePolicy;

public class PrivateIndividual  implements PricePolicy{

    @Override
    public String priceVAT(double x) {
        double inkVAT = x * 1.25;
        double diff = inkVAT - x;
        return inkVAT + " kr."+"\nInkl. moms 25 % : "+ diff +" kr.";}

    @Override
    public double discount(double x) {
        return x * 0.7; }
}
