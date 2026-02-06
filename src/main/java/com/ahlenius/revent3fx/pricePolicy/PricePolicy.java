package com.ahlenius.revent3fx.pricePolicy;

import java.math.BigDecimal;

public interface PricePolicy {

    String priceVAT(BigDecimal x);
    BigDecimal discount(BigDecimal x);


}
