package com.what3words.javawrapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.what3words.javawrapper.response.APIResponse;
import com.what3words.javawrapper.response.APIResponse.What3WordsError;
import com.what3words.javawrapper.response.ConvertToCoordinates;

public class ConvertToCoordinatesTest {
    What3WordsV3 api = new What3WordsV3(System.getenv("PROD_API_KEY"));
    
    @Test
    public void invalid3waTest() {
        ConvertToCoordinates response = api.convertToCoordinates("filled").execute();
        
        What3WordsError error = response.getError();

        assertEquals(error, APIResponse.What3WordsError.BAD_WORDS);
    }
    
    @Test
    public void valid3waTest() {
        ConvertToCoordinates coords = api.convertToCoordinates("filled.count.soap").execute();
        
        assertEquals("filled.count.soap", coords.getWords());
        assertEquals("GB", coords.getCountry());

        assertEquals(-0.195543, coords.getSquare().getSouthwest().getLng(),0);
        assertEquals(51.520833, coords.getSquare().getSouthwest().getLat(), 0);
        assertEquals(-0.195499, coords.getSquare().getNortheast().getLng(), 0);
        assertEquals(51.52086, coords.getSquare().getNortheast().getLat(), 0);

        assertEquals(-0.195521, coords.getCoordinates().getLng(),0);
        assertEquals(51.520847, coords.getCoordinates().getLat(), 0);

        assertEquals("en", coords.getLanguage());
        assertEquals("https://w3w.co/filled.count.soap", coords.getMap());
        assertEquals("Bayswater, London", coords.getNearestPlace());
    }

    @Test
    public void valid3waWithLocaleTest() {
        ConvertToCoordinates coords = api.convertToCoordinates("seruuhen.zemseg.dagaldah").execute();

        assertEquals("seruuhen.zemseg.dagaldah", coords.getWords());
        assertEquals("GB", coords.getCountry());

        assertEquals(-0.195543, coords.getSquare().getSouthwest().getLng(),0);
        assertEquals(51.520833, coords.getSquare().getSouthwest().getLat(), 0);
        assertEquals(-0.195499, coords.getSquare().getNortheast().getLng(), 0);
        assertEquals(51.52086, coords.getSquare().getNortheast().getLat(), 0);

        assertEquals(-0.195521, coords.getCoordinates().getLng(),0);
        assertEquals(51.520847, coords.getCoordinates().getLat(), 0);

        assertEquals("mn", coords.getLanguage());
        assertEquals("mn_la", coords.getLocale());
        assertEquals("https://w3w.co/seruuhen.zemseg.dagaldah", coords.getMap());
        assertEquals("Лондон", coords.getNearestPlace());
    }
}

