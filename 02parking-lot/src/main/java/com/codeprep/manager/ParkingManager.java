package com.codeprep.manager;

import com.codeprep.constant.VehicleSize;
import com.codeprep.parkingspot.ParkingSpot;
import com.codeprep.vehicle.Vehicle;

import java.util.List;
import java.util.Map;

public class ParkingManager {

    private Map<VehicleSize, List<ParkingSpot>> availableSpots;
    private Map<Vehicle, ParkingSpot> vehicleToSpotMap;

    public ParkingManager(Map<VehicleSize, List<ParkingSpot>> availableSpots, Map<Vehicle, ParkingSpot> vehicleToSpotMap) {
        this.availableSpots = availableSpots;
        this.vehicleToSpotMap = vehicleToSpotMap;
    }

    public ParkingSpot findSpotForVehicle(Vehicle vehicle) {

        for(VehicleSize size : VehicleSize.values()) {
            if(size.ordinal() >= vehicle.getSize().ordinal()) {
                List<ParkingSpot> spots = availableSpots.get(size);
                if(spots != null) {
                    for(ParkingSpot spot : spots) {
                        if(spot.isAvailable()) {
                            return spot;
                        }
                    }
                }
            }
        }
        return null;
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = findSpotForVehicle(vehicle);
        if(spot != null) {
            spot.occupy(vehicle);
            vehicleToSpotMap.put(vehicle, spot);
            availableSpots.get(spot.getSize()).remove(spot);
        }
        return spot;
    }

    public void unParkVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = vehicleToSpotMap.remove(vehicle);
        if(parkingSpot != null) {
            parkingSpot.vacate();
            availableSpots.get(parkingSpot.getSize()).add(parkingSpot);
        }
    }

    public Map<VehicleSize, List<ParkingSpot>> getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(Map<VehicleSize, List<ParkingSpot>> availableSpots) {
        this.availableSpots = availableSpots;
    }

    public Map<Vehicle, ParkingSpot> getVehicleToSpotMap() {
        return vehicleToSpotMap;
    }

    public void setVehicleToSpotMap(Map<Vehicle, ParkingSpot> vehicleToSpotMap) {
        this.vehicleToSpotMap = vehicleToSpotMap;
    }
}
