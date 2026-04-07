package com.codeprep;

public class SharedFareStrategy implements FareStrategy {

    @Override
    public double calculateFare(Vehicle vehicle, double distance) {
        return vehicle.getFarePerKm() * distance * 0.50;
    }
}
