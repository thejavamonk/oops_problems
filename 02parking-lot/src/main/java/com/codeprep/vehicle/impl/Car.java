package com.codeprep.vehicle.impl;

import com.codeprep.constant.VehicleSize;
import com.codeprep.vehicle.Vehicle;

public class Car implements Vehicle {

    private String licensePlate;
    private final VehicleSize size = VehicleSize.MEDIUM;

    public Car(String licensePlate) {
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
