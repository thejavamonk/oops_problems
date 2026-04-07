package com.codeprep;

public class Location {

    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double calculateDistance(Location location) {
        // Euclidean distance
        double dx = this.latitude - location.getLatitude();
        double dy = this.longitude - location.getLongitude();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
