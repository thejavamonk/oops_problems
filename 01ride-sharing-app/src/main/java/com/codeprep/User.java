package com.codeprep;

public abstract class User {

    protected String username;
    protected Location location;

    public User(Location location, String username) {
        this.username = username;
        this.location = location;
    }

    public abstract void notify(String msg);

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
