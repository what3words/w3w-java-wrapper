package com.what3words.javawrapper.response;

public class Coordinates {
    private double lng;
    private double lat;

    public Coordinates(double lat, double lng) {
        this.lng = lng;
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }
}