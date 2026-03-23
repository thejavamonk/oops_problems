package com.codeprep;

public class LuxuryFareStrategy implements FareStrategy {

    @Override
    public double calculateFare(Vehicle vehicle, double distance) {
        return vehicle.getFarePerKm() * distance * 1.5;
    }
}
