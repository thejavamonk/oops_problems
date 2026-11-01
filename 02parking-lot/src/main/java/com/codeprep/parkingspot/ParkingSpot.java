package com.codeprep.parkingspot;

import com.codeprep.constant.VehicleSize;
import com.codeprep.vehicle.Vehicle;

public interface ParkingSpot {

    boolean isAvailable();
    void occupy(Vehicle vehicle);
    void vacate();
    VehicleSize getSize();
}
