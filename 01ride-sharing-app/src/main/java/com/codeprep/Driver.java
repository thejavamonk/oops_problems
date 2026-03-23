package com.codeprep;

public class Driver extends User{

    private Vehicle vehicle;

    public Driver(Location location, String username, Vehicle vehicle) {
        super(location, username);
        this.vehicle = vehicle;
    }

    public void notify(String msg){
        System.out.println("to Driver: "+msg);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
