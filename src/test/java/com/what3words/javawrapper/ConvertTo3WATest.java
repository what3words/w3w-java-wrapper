package com.what3words.javawrapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.APIResponse;
import com.what3words.javawrapper.response.ConvertTo3WA;
import com.what3words.javawrapper.response.APIResponse.Error;

public class ConvertTo3WATest {
    What3WordsV3 api = new What3WordsV3(System.getenv("W3W_API_KEY"));
    
    @Test
    public void twoInvalidCoordsTest() {
        ConvertTo3WA response = api.convertTo3wa(new Coordinates(-200, -200)).execute();
        
        Error error = response.getError();

        assertEquals(APIResponse.Error.BAD_COORDINATES, error);
    }
    
    @Test
    public void validCoordsTest() {
        ConvertTo3WA twa = api.convertTo3wa(new Coordinates(51.520847, -0.19552100)).execute();
        
        assertEquals("filled.count.soap", twa.getWords());
        assertEquals("GB", twa.getCountry());
        
        assertEquals(-0.195543, twa.getSquare().getSouthwest().getLng(), 0);
        assertEquals(51.520833, twa.getSquare().getSouthwest().getLat(),0);
        assertEquals(-0.195499, twa.getSquare().getNortheast().getLng(),0);
        assertEquals(51.52086, twa.getSquare().getNortheast().getLat(), 0);
        
        assertEquals(-0.195521,  twa.getCoordinates().getLng(),0);
        assertEquals(51.520847, twa.getCoordinates().getLat(), 0);
        
        assertEquals("en", twa.getLanguage());
        assertEquals("https://w3w.co/filled.count.soap", twa.getMap());
        assertEquals("Bayswater, London", twa.getNearestPlace());
    }
}
