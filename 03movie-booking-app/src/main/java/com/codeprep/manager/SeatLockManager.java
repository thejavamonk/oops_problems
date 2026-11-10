package com.codeprep.manager;

import com.codeprep.cinema.Screening;
import com.codeprep.cinema.Seat;
import com.codeprep.lock.SeatLock;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SeatLockManager {

    private final Map<String, SeatLock> lockedSeats;
    private final Duration lockDuration;

    public SeatLockManager(Duration lockDuration) {
        this.lockDuration = lockDuration;
        this.lockedSeats = new ConcurrentHashMap<>();
    }

    public synchronized boolean lockSeat(Screening screening, Seat seat, String userId){
        String lockKey = generateLockKey(screening, seat);

        //clean up lock if expired
        cleanUpLockIfExpired(lockKey);

        //check if seat is already locked
        if(isLocked(screening, seat)){
            return false;
        }

        //lock the seat
        SeatLock seatLock = new SeatLock(userId, LocalDateTime.now().plus(lockDuration));
        lockedSeats.put(lockKey, seatLock);
        return true;
    }

    public synchronized boolean isLocked(Screening screening, Seat seat){
        String lockKey = generateLockKey(screening, seat);

        // clean up lock if expired (on demand clean up)
        cleanUpLockIfExpired(lockKey);

        // if we reach here 1. either no lock exists or its valid
        return lockedSeats.containsKey(lockKey);
    }

    private void cleanUpLockIfExpired(String lockKey) {
        SeatLock lock = lockedSeats.get(lockKey);
        if (lock != null && lock.isExpired()) {
            lockedSeats.remove(lockKey);
        }
    }

    private String generateLockKey(Screening screening, Seat seat) {
        return screening.getId() + "-" + seat.getSeatNo();
    }
}
