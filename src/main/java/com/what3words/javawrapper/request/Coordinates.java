package com.what3words.javawrapper.request;

import java.io.Serializable;

/**
 * A Coordinate represents (latitude, longitude) coordinates encoded according to the World Geodetic System (WGS84).
 */
public final class Coordinates implements Serializable {
    public final double lat;
    public final double lng;

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    /**
     * Creates a new <code>Coordinates</code> instance.
     * @param lat the latitude
     * @param lng the longitude
     */
    public Coordinates(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    /**
     * Compares this <code>Coordinates</code> instance to the specified object. The result is true if and only if 
     * the argument is not <code>null</code> and is a <code>Coordinates</code> object that represents the 
     * same latitude and longitude as this object.
     * @return <code>true</code> if the given object represents a <code>Coordinates</code> equivalent to this 
     * <code>Coordinates</code>, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        return Double.compare(that.lat, lat) == 0 && Double.compare(that.lng, lng) == 0;
    }

    /**
     * Returns a hash code for this <code>Coordinates</code> instance.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(lat);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Returns a String object representing this <code>Coordinates</code> instance.
     */
    @Override
    public String toString() {
        return "(" + lat + ", " + lng + ')';
    }
}
