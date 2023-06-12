package com.what3words.javawrapper.response;

import java.io.Serializable;

public class Coordinates implements Serializable {
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