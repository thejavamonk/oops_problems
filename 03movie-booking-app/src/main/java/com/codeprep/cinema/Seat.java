package com.codeprep.cinema;

import com.codeprep.price.PricingStrategy;

public class Seat {

    private final String seatNo;
    private final PricingStrategy pricingStrategy;

    public Seat(String seatNo, PricingStrategy pricingStrategy) {
        this.seatNo = seatNo;
        this.pricingStrategy = pricingStrategy;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }
}
