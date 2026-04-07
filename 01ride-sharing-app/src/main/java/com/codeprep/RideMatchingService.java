package com.codeprep;

import java.util.ArrayList;
import java.util.List;

public class RideMatchingService {

    private List<Driver> availableDrivers = new ArrayList<>();

    public void addDriver(Driver driver) {
        availableDrivers.add(driver);
    }

    public void requestRide(Passenger passenger, double distance, FareStrategy fareStrategy) {

        // base case
        if (availableDrivers.isEmpty()) {
            // mechanism to notify the passenger
            passenger.notify("No drivers found");
            return;
        }

        // find the nearest driver
        Driver nearestDriver = findNearestDriver(passenger.getLocation());

        // Mediator
        availableDrivers.remove(nearestDriver);
        Ride ride = new Ride(passenger, nearestDriver, distance, fareStrategy, RideStatus.SCHEDULED);
        ride.calculateFare();

        passenger.notify("Ride scheduled with fare + Rs. " + ride.getFare());
        nearestDriver.notify("You have a new ride request for " + ride.getFare());

        // Change the status of the Ride
        delayToSimulateRideProgress(1000);
        ride.updateStatus(RideStatus.ONGOING);

        delayToSimulateRideProgress(2000);
        ride.updateStatus(RideStatus.COMPLETED);

        // Change the status of the ride after the ride is finished
        availableDrivers.add(nearestDriver);
    }

    private static void delayToSimulateRideProgress(long delayInMillis) {
        try {
            Thread.sleep(delayInMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Driver findNearestDriver(Location location) {
        Driver assignedDriver = null;
        double minDistance = Double.MAX_VALUE;

        for (Driver driver : availableDrivers) {
            double distance = driver.getLocation().calculateDistance(location);
            if (distance < minDistance) {
                assignedDriver = driver;
            }
        }
        return assignedDriver;
    }
}
