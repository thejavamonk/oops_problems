package com.codeprep.price;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal getPrice();
}
