package com.codeprep.fare;

import com.codeprep.ticket.Ticket;

import java.math.BigDecimal;

public interface FareStrategy {

    BigDecimal calculateFare(Ticket ticket, BigDecimal inputFare);
}
