package com.codeprep;

public class Car extends Vehicle {

    public Car(String numberPlate) {
        super(numberPlate);
    }

    @Override
    public double getFarePerKm() {
        return 30;
    }
}
