package com.what3words.javawrapper;

import com.what3words.javawrapper.request.BoundingBox;
import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.APIResponse;
import com.what3words.javawrapper.response.GridSection;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GridSectionTest {
    What3WordsV3 api = new What3WordsV3(System.getenv("W3W_API_KEY"));

    @Test
    public void invalidGridSectionTest() {
        GridSection response = api.gridSection(new BoundingBox(new Coordinates(51.0, 0.0), new Coordinates(52.0, 0.1))).execute();

        APIResponse.What3WordsError error = response.getError();

        assertEquals(error, APIResponse.What3WordsError.BAD_BOUNDING_BOX_TOO_BIG);
    }

    @Test
    public void validGridSectionTest() {
        GridSection response = api.gridSection(new BoundingBox(new Coordinates(51.1122,0.12221), new Coordinates(51.1333,0.1223))).execute();

        assertTrue(response.isSuccessful());
        assertTrue(response.getLines().size() > 0);
    }
}

