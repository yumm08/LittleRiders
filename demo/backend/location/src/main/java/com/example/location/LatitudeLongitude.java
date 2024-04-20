package com.example.location;


public class LatitudeLongitude {

    private int sequence;

    public static int SEQUENCE = 0;

    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }


    public int getSequence(){
        return sequence;
    }

    public LatitudeLongitude(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.sequence = SEQUENCE++;
    }

}
