package com.codeprep;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RideSharingAppTest {

    @Test
    void testLocationCreation() {
        Location location = new Location(12.9716, 77.5946);
        assertEquals(12.9716, location.getLatitude(), 0.0001);
        assertEquals(77.5946, location.getLongitude(), 0.0001);
    }

    @Test
    void testLocationDistanceCalculation() {
        Location loc1 = new Location(0, 0);
        Location loc2 = new Location(3, 4);
        assertEquals(5.0, loc1.calculateDistance(loc2), 0.0001);
    }

    @Test
    void testCarCreation() {
        Vehicle car = new Car("AB123CD");
        assertNotNull(car);
        assertEquals("AB123CD", car.getNumberPlate());
    }

    @Test
    void testCarFarePerKm() {
        Vehicle car = new Car("AB123CD");
        assertTrue(car.getFarePerKm() > 0, "Car fare per km should be positive");
    }

    @Test
    void testBikeCreation() {
        Vehicle bike = new Bike("XY987Z");
        assertNotNull(bike);
        assertEquals("XY987Z", bike.getNumberPlate());
    }

    @Test
    void testDriverCreation() {
        Location location = new Location(12.9352, 77.6245);
        Vehicle car = new Car("AB123CD");
        Driver driver = new Driver(location, "alice", car);
        assertNotNull(driver);
        assertEquals("alice", driver.getUsername());
        assertEquals(car, driver.getVehicle());
    }

    @Test
    void testPassengerCreation() {
        Location location = new Location(12.9716, 77.5946);
        Passenger passenger = new Passenger(location, "john");
        assertNotNull(passenger);
        assertEquals("john", passenger.getUsername());
    }

    @Test
    void testRideMatchingServiceAddDriver() {
        RideMatchingService service = new RideMatchingService();
        Location location = new Location(12.9352, 77.6245);
        Driver driver = new Driver(location, "alice", new Car("AB123CD"));
        assertDoesNotThrow(() -> service.addDriver(driver));
    }

    @Test
    void testStandardFareStrategy() {
        FareStrategy strategy = new StandardFareStrategy();
        Vehicle car = new Car("AB123CD");
        double fare = strategy.calculateFare(car, 10);
        assertTrue(fare > 0, "Fare should be positive for a valid distance");
    }

    @Test
    void testLuxuryFareStrategy() {
        FareStrategy strategy = new LuxuryFareStrategy();
        Vehicle car = new Car("AB123CD");
        double fare = strategy.calculateFare(car, 10);
        assertTrue(fare > 0, "Fare should be positive for a valid distance");
    }

    @Test
    void testSharedFareStrategy() {
        FareStrategy strategy = new SharedFareStrategy();
        Vehicle car = new Car("AB123CD");
        double fare = strategy.calculateFare(car, 10);
        assertTrue(fare > 0, "Fare should be positive for a valid distance");
    }

    @Test
    void testLuxuryFareHigherThanStandard() {
        Vehicle car = new Car("AB123CD");
        FareStrategy standard = new StandardFareStrategy();
        FareStrategy luxury = new LuxuryFareStrategy();
        double distance = 10;
        assertTrue(luxury.calculateFare(car, distance) > standard.calculateFare(car, distance),
                "Luxury fare should be higher than standard fare");
    }

    @Test
    void testSharedFareLowerThanStandard() {
        Vehicle car = new Car("AB123CD");
        FareStrategy standard = new StandardFareStrategy();
        FareStrategy shared = new SharedFareStrategy();
        double distance = 10;
        assertTrue(shared.calculateFare(car, distance) < standard.calculateFare(car, distance),
                "Shared fare should be lower than standard fare");
    }
}


