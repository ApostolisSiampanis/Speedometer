package com.example.speedometer;

public class TempLocationModel {

    private double longitude;
    private double latitude;
    private double speed;
    private String timestamp;

    public TempLocationModel(double longitude, double latitude, double speed, String timestamp) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.speed = speed;
        this.timestamp = timestamp;
    }

    public TempLocationModel() {
    }

    @Override
    public String toString() {
        return  "longitude= " + longitude + ",\n" +
                "latitude= " + latitude + ",\n" +
                "speed= " + speed + ",\n" +
                "timestamp= " + timestamp;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
