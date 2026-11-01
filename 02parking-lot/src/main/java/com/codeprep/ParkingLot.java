package com.codeprep;

import com.codeprep.manager.FareCalculator;
import com.codeprep.manager.ParkingManager;
import com.codeprep.parkingspot.ParkingSpot;
import com.codeprep.ticket.Ticket;
import com.codeprep.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ParkingLot {

    private final ParkingManager parkingManager;
    private final FareCalculator fareCalculator;

    public ParkingLot(ParkingManager parkingManager, FareCalculator fareCalculator) {
        this.parkingManager = parkingManager;
        this.fareCalculator = fareCalculator;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = parkingManager.parkVehicle(vehicle);

        if(spot != null){
            return new Ticket(vehicle, spot, LocalDateTime.now());
        }else{
            System.out.println("No available parking spot for vehicle: " + vehicle.getLicensePlate());
        }
        return null;
    }

    public void leaveVehicle(Ticket ticket){

        if(ticket != null && ticket.getExitTime() == null){
            ticket.setExitTime(LocalDateTime.now());
            parkingManager.unParkVehicle(ticket.getVehicle());
            BigDecimal fare = fareCalculator.calculateFare(ticket);
            System.out.println("Parking fare for vehicle " + ticket.getVehicle().getLicensePlate() + ": " + fare);
        }else {
            System.out.println("Invalid ticket or vehicle has already left.");
        }
    }
}
