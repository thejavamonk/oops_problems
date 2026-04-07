package com.codeprep;

public class Passenger extends User{

    public Passenger(Location location, String username) {
        super(location, username);
    }

    public void notify(String msg){
        System.out.println("to Passenger: " +msg);
    }
}
