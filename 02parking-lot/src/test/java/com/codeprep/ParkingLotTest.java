package com.codeprep;

import com.codeprep.constant.VehicleSize;
import com.codeprep.fare.FareStrategy;
import com.codeprep.manager.FareCalculator;
import com.codeprep.manager.ParkingManager;
import com.codeprep.parkingspot.ParkingSpot;
import com.codeprep.parkingspot.impl.RegularSpot;
import com.codeprep.ticket.Ticket;
import com.codeprep.vehicle.Vehicle;
import com.codeprep.vehicle.impl.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingLotTest {

    private ParkingSpot spot;
    private ParkingManager parkingManager;
    private ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        spot = new RegularSpot(1);

        Map<VehicleSize, List<ParkingSpot>> availableSpots = new EnumMap<>(VehicleSize.class);
        availableSpots.put(VehicleSize.MEDIUM, new ArrayList<>(List.of(spot)));

        parkingManager = new ParkingManager(availableSpots, new HashMap<>());
        FareStrategy flatFare = (ticket, inputFare) -> new BigDecimal("10");
        parkingLot = new ParkingLot(parkingManager, new FareCalculator(List.of(flatFare)));
    }

    @Test
    void parkVehicle_returnsTicketAndOccupiesSpot() {
        Vehicle car = new Car("KA-01-1234");

        Ticket ticket = parkingLot.parkVehicle(car);

        assertNotNull(ticket);
        assertSame(car, ticket.getVehicle());
        assertSame(spot, ticket.getParkingSpot());
        assertNotNull(ticket.getEntryTime());
        assertNull(ticket.getExitTime());
        assertTrue(!spot.isAvailable());
    }

    @Test
    void parkVehicle_returnsNullWhenLotIsFull() {
        parkingLot.parkVehicle(new Car("KA-01-1111"));

        Ticket ticket = parkingLot.parkVehicle(new Car("KA-01-2222"));

        assertNull(ticket);
    }

    @Test
    void leaveVehicle_setsExitTimeAndFreesSpot() {
        Ticket ticket = parkingLot.parkVehicle(new Car("KA-01-1234"));

        parkingLot.leaveVehicle(ticket);

        assertNotNull(ticket.getExitTime());
        assertTrue(spot.isAvailable());
        assertTrue(parkingManager.getVehicleToSpotMap().isEmpty());
    }

    @Test
    void leaveVehicle_allowsSpotToBeReused() {
        Ticket first = parkingLot.parkVehicle(new Car("KA-01-1111"));
        parkingLot.leaveVehicle(first);

        Ticket second = parkingLot.parkVehicle(new Car("KA-01-2222"));

        assertNotNull(second);
        assertSame(spot, second.getParkingSpot());
    }

    @Test
    void leaveVehicle_secondCallWithSameTicketIsIgnored() {
        Vehicle car = new Car("KA-01-1234");
        Ticket ticket = parkingLot.parkVehicle(car);
        parkingLot.leaveVehicle(ticket);
        var exitTime = ticket.getExitTime();

        // a new vehicle takes the freed spot; replaying the old ticket must not free it
        parkingLot.parkVehicle(new Car("KA-01-9999"));
        parkingLot.leaveVehicle(ticket);

        assertEquals(exitTime, ticket.getExitTime());
        assertTrue(!spot.isAvailable());
    }

    @Test
    void leaveVehicle_nullTicketDoesNothing() {
        parkingLot.parkVehicle(new Car("KA-01-1234"));

        parkingLot.leaveVehicle(null);

        assertTrue(!spot.isAvailable());
    }
}
