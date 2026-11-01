package com.codeprep.vehicle.impl;

import com.codeprep.constant.VehicleSize;
import com.codeprep.vehicle.Vehicle;

public class Bike implements Vehicle {

    private String licensePlate;
    private final VehicleSize size = VehicleSize.SMALL;

    public Bike(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public VehicleSize getSize() {
        return size;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
