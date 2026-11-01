package com.codeprep.fare.impl;

import com.codeprep.fare.FareStrategy;
import com.codeprep.ticket.Ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PeakHourFareStarategy implements FareStrategy {

    private static final BigDecimal PEAK_HOUR_FARE_MULTIPLIER = new BigDecimal("1.5");

    public PeakHourFareStarategy() {
    }

    @Override
    public BigDecimal calculateFare(Ticket ticket, BigDecimal inputFare) {
        if (isPeakHour(ticket.getEntryTime())) {
            return inputFare.multiply(PEAK_HOUR_FARE_MULTIPLIER);
        }
        return inputFare;
    }

    private boolean isPeakHour(LocalDateTime time){
        int hour = time.getHour();
        return (hour >= 8 && hour < 10) || (hour >= 17 && hour < 19);
    }
}
