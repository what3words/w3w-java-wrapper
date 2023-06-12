package com.what3words.javawrapper;

import com.what3words.javawrapper.request.ConvertTo3WARequest;
import org.junit.Test;

import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.APIResponse;
import com.what3words.javawrapper.response.ConvertTo3WA;
import com.what3words.javawrapper.response.APIResponse.What3WordsError;

import static org.junit.Assert.*;

public class ConvertTo3WATest {
    What3WordsV3 api = new What3WordsV3(System.getenv("PROD_API_KEY"));
    
    @Test
    public void twoInvalidCoordsTest() {
        ConvertTo3WA response = api.convertTo3wa(new Coordinates(-200, -200)).execute();
        
        What3WordsError error = response.getError();

        assertEquals(APIResponse.What3WordsError.BAD_COORDINATES, error);
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

    @Test
    public void validCoordsWithLanguageTest() {
        ConvertTo3WARequest.Builder twaBuilder = api.convertTo3wa(new Coordinates(51.520847, -0.19552100)).language("pt");
        assertEquals("pt", twaBuilder.getLanguage());

        ConvertTo3WA twa = twaBuilder.execute();

        assertEquals("refrigerando.valem.touro", twa.getWords());
        assertEquals("GB", twa.getCountry());

        assertEquals(-0.195543, twa.getSquare().getSouthwest().getLng(), 0);
        assertEquals(51.520833, twa.getSquare().getSouthwest().getLat(),0);
        assertEquals(-0.195499, twa.getSquare().getNortheast().getLng(),0);
        assertEquals(51.52086, twa.getSquare().getNortheast().getLat(), 0);

        assertEquals(-0.195521,  twa.getCoordinates().getLng(),0);
        assertEquals(51.520847, twa.getCoordinates().getLat(), 0);

        assertEquals("pt", twa.getLanguage());
        assertEquals("https://w3w.co/refrigerando.valem.touro", twa.getMap());
        assertEquals("Londres, London", twa.getNearestPlace());
    }

    @Test
    public void validCoordsWithLocaleTest() {
        ConvertTo3WARequest.Builder twaBuilder = api.convertTo3wa(new Coordinates(51.520847, -0.19552100)).locale("mn_la");
        assertNull(twaBuilder.getLanguage());
        assertEquals("mn_la", twaBuilder.getLocale());

        ConvertTo3WA twa = twaBuilder.execute();

        assertEquals("seruuhen.zemseg.dagaldah", twa.getWords());
        assertEquals("GB", twa.getCountry());

        assertEquals(-0.195543, twa.getSquare().getSouthwest().getLng(), 0);
        assertEquals(51.520833, twa.getSquare().getSouthwest().getLat(),0);
        assertEquals(-0.195499, twa.getSquare().getNortheast().getLng(),0);
        assertEquals(51.52086, twa.getSquare().getNortheast().getLat(), 0);

        assertEquals(-0.195521,  twa.getCoordinates().getLng(),0);
        assertEquals(51.520847, twa.getCoordinates().getLat(), 0);

        assertEquals("mn", twa.getLanguage());
        assertEquals("mn_la", twa.getLocale());
        assertEquals("https://w3w.co/seruuhen.zemseg.dagaldah", twa.getMap());
        assertEquals("Лондон, London", twa.getNearestPlace());
    }
}
