package com.codeprep.parkingspot.impl;

import com.codeprep.constant.VehicleSize;
import com.codeprep.parkingspot.ParkingSpot;
import com.codeprep.vehicle.Vehicle;

public class CompactSpot implements ParkingSpot {

    private int spotNo;
    private Vehicle vehicle;

    public CompactSpot(int spotNo) {
        this.spotNo = spotNo;
    }

    @Override
    public boolean isAvailable() {
        return vehicle == null;
    }

    @Override
    public void occupy(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void vacate() {
        this.vehicle = null;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }

    public int getSpotNo() {
        return spotNo;
    }

    public void setSpotNo(int spotNo) {
        this.spotNo = spotNo;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
