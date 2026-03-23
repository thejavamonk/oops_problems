package com.codeprep;

public class Ride {

    private Passenger passenger;
    private Driver driver;
    private double distance;
    private double fare;
    private FareStrategy fareStrategy;
    private RideStatus status;

    public Ride(Passenger passenger, Driver driver, double distance, FareStrategy fareStrategy, RideStatus status) {
        this.passenger = passenger;
        this.driver = driver;
        this.distance = distance;
        this.fareStrategy = fareStrategy;
        this.status = status;
    }

    public void calculateFare() {
        this.fare = fareStrategy.calculateFare(driver.getVehicle(), distance);
    }

    public void updateStatus(RideStatus status) {
        this.status = status;
        notifyUsers(status);
    }

    private void notifyUsers(RideStatus status) {
        passenger.notify("Your ride is " + status + ".");
        driver.notify("Ride status is " + status + ".");
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public FareStrategy getFareStrategy() {
        return fareStrategy;
    }

    public void setFareStrategy(FareStrategy fareStrategy) {
        this.fareStrategy = fareStrategy;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }
}
