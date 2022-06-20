package com.what3words.javawrapper.response;

public class Line {
    private Coordinates start;
    private Coordinates end;

    public Line(double startLat, double startLng, double endLat, double endLng) {
        start = new Coordinates(startLat, startLng);
        end = new Coordinates(endLat, endLng);
    }

    public Coordinates getStart() {
        return start;
    }

    public Coordinates getEnd() {
        return end;
    }
}