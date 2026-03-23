package com.codeprep;

public class Application {
    static void main() {

        Location location1 = new Location(12.9716, 77.5946);
        Location location2 = new Location(12.9352, 77.6245);
        Location location3 = new Location(13.0352, 77.6175);

        Vehicle car = new Car("AB123CD");
        Vehicle bike = new Bike("XY987Z");

        Driver driver1 = new Driver(location2, "alice@rideshare.com", car);
        Driver driver2 = new Driver(location3, "john@rideshare.com", bike);

        Passenger passenger = new Passenger(location1, "john@rideshare.com");

        RideMatchingService rideMatchingService = new RideMatchingService();
        rideMatchingService.addDriver(driver1);
        rideMatchingService.addDriver(driver2);
        rideMatchingService.requestRide(passenger, 10, new StandardFareStrategy());
    }
}
