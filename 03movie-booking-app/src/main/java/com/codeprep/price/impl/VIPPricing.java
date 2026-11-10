package com.codeprep.price.impl;

import com.codeprep.price.PricingStrategy;

import java.math.BigDecimal;

public class VIPPricing implements PricingStrategy {

    private final BigDecimal price;

    public VIPPricing(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}
