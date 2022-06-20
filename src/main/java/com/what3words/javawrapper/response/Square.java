package com.what3words.javawrapper.response;

public class Square {
    private Coordinates southwest;
    private Coordinates northeast;

    public Square(double neLat, double neLng, double swLat, double swLng) {
        southwest = new Coordinates(swLat, swLng);
        northeast = new Coordinates(neLat, neLng);
    }

    public Coordinates getSouthwest() {
        return southwest;
    }

    public Coordinates getNortheast() {
        return northeast;
    }
}