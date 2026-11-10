package com.codeprep.lock;

import java.time.LocalDateTime;

public class SeatLock {

    private final String userId;
    private final LocalDateTime expirationTime;

    public SeatLock(String userId, LocalDateTime expirationTime) {
        this.userId = userId;
        this.expirationTime = expirationTime;
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expirationTime);
    }

}
