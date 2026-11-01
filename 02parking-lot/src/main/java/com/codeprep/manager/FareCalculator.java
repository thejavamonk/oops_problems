package com.codeprep.manager;

import com.codeprep.fare.FareStrategy;
import com.codeprep.ticket.Ticket;

import java.math.BigDecimal;
import java.util.List;

public class FareCalculator {

    private final List<FareStrategy> fareStrategies;

    public FareCalculator(List<FareStrategy> fareStrategies) {
        this.fareStrategies = fareStrategies;
    }

    public BigDecimal calculateFare(Ticket ticket) {
        BigDecimal fare = BigDecimal.ZERO;
        for (FareStrategy fareStrategy : fareStrategies) {
            fare = fare.add(fareStrategy.calculateFare(ticket, fare));
        }
        return fare;
    }
}
