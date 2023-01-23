package com.what3words.javawrapper.response;

import com.what3words.javawrapper.request.BoundingBox;

public class Square {
    private Coordinates southwest;
    private Coordinates northeast;

    public Square(double neLat, double neLng, double swLat, double swLng) {
        southwest = new Coordinates(swLat, swLng);
        northeast = new Coordinates(neLat, neLng);
    }

    public Square(BoundingBox box) {
        southwest = new Coordinates(box.sw.lat, box.sw.lng);
        northeast = new Coordinates(box.ne.lat, box.ne.lng);
    }

    public Coordinates getSouthwest() {
        return southwest;
    }

    public Coordinates getNortheast() {
        return northeast;
    }
}