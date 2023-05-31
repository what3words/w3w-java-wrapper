package com.what3words.javawrapper.request;

import java.io.Serializable;

/**
 * This class defines a <code>BoundingBox</code> which which represents a range of latitudes and longitudes.
 */
public final class BoundingBox implements Serializable {
    public final Coordinates sw;
    public final Coordinates ne;

    /**
     * Creates a new instance of a <code>BoundingBox</code>.
     * @param sw the coordinates of the southwest corner
     * @param ne the coordinates of the northeast corner
     */
    public BoundingBox(Coordinates sw, Coordinates ne) {
        this.sw = sw;
        this.ne = ne;
    }

    
    /**
     * Compares this <code>BoundingBox</code> to the specified object. The result is true if and only if 
     * the argument is not <code>null</code> and is a <code>BoundingBox</code> object that represents the 
     * same southwest and northeast coordinates as this object.
     * @return <code>true</code> if the given object represents a <code>BoundingBox</code> equivalent to this 
     * <code>BoundingBox</code>, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoundingBox that = (BoundingBox) o;

        return (sw != null ? sw.equals(that.sw) : that.sw == null) &&
                (ne != null ? ne.equals(that.ne) : that.ne == null);
    }

    /**
     * Returns a hash code for this <code>BoundingBox</code>.
     */
    @Override
    public int hashCode() {
        int result = sw != null ? sw.hashCode() : 0;
        result = 31 * result + (ne != null ? ne.hashCode() : 0);
        return result;
    }

    /**
     * Returns a String object representing this <code>BoundingBox</code>.
     */
    @Override
    public String toString() {
        return "BoundingBox{" +
                "sw=" + sw +
                ", ne=" + ne +
                '}';
    }
}
