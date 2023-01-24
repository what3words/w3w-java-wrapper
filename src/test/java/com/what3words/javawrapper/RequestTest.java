package com.what3words.javawrapper;

import com.what3words.javawrapper.request.BoundingBox;
import com.what3words.javawrapper.request.Coordinates;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {

    @Test
    public void boundingBoxTest() {
        BoundingBox bb1 = new BoundingBox(new Coordinates(5.1,0.0), new Coordinates(5.2,0.1));
        BoundingBox bb2 = new BoundingBox(new Coordinates(5.1,0.0), new Coordinates(5.2,0.2));
        BoundingBox bb3 = new BoundingBox(new Coordinates(5.1,0.0), new Coordinates(5.2,0.2));
        assertNotNull(bb1.toString());
        assertTrue(bb1.toString().contains(bb1.sw.toString()));
        assertEquals(bb2, bb3);
        assertEquals(bb2.hashCode(), bb3.hashCode());
    }

    @Test
    public void coordinatesTest() {
        Coordinates c1 = new Coordinates(5.1,0.0);
        Coordinates c2 = new Coordinates(5.1,0.0);
        Coordinates c3 = new Coordinates(5.1,1.0);
        Coordinates c4 = new Coordinates(5.2,0.0);
        assertNotNull(c1.toString());
        assertTrue(c1.toString().contains("5.1"));
        assertEquals(c1, c2);
        assertNotEquals(c1, null);
        assertNotEquals(c1, c3);
        assertNotEquals(c1, c4);
        assertEquals(c1.getLat(), c2.getLat(), 0.0);
        assertEquals(c1.getLng(), c2.getLng(), 0.0);
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}