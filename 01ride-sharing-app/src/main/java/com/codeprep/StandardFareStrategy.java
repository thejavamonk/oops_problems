package com.codeprep;

public class StandardFareStrategy implements FareStrategy {

    @Override
    public double calculateFare(Vehicle vehicle, double distance) {
        return vehicle.getFarePerKm() * distance;
    }
}
