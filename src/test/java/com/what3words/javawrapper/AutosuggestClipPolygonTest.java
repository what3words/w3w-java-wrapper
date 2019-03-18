package com.what3words.javawrapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.APIResponse;
import com.what3words.javawrapper.response.Autosuggest;
import com.what3words.javawrapper.response.Suggestion;
import com.what3words.javawrapper.response.APIResponse.Error;

public class AutosuggestClipPolygonTest {

    What3WordsV3 api = new What3WordsV3("what3words-api-key");
    
    @Test
    public void testPolygonClip() {
        // Polygon must have at least 4 entries
        Coordinates p1 = new Coordinates(51, -1);
        Coordinates p2 = new Coordinates(53, 0);
        Coordinates p3 = new Coordinates(51, 1);
        
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .clipToPolygon(p1, p2, p3, p1)
                .execute();
        
        List<Suggestion> suggestions = autosuggest.getSuggestions();

        boolean found = false;
        for (Suggestion s : suggestions) {
            if (s.getWords().equalsIgnoreCase("index.home.raft")) {
                found = true;
            }
        }

        assertTrue("Can't find index.home.raft", found);
    }

    @Test
    public void testPolygonClipWithTooFewPoints() {
        // Polygon must have at least 4 entries
        Coordinates p1 = new Coordinates(51, -1);
        Coordinates p2 = new Coordinates(53, 0);
        Coordinates p3 = new Coordinates(51, 1);
        
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .clipToPolygon(p1, p2, p3)
                .execute();
        
        Error error = autosuggest.getError();

        assertEquals(APIResponse.Error.BAD_CLIP_TO_POLYGON, error);
    }

    @Test
    public void testPolygonClipWithHugeLongitude() {
        //Polygon must have at least 4 entries
        Coordinates p1 = new Coordinates(51, -1-180);
        Coordinates p2 = new Coordinates(53, 0);
        Coordinates p3 = new Coordinates(51, 1+180);
        
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .clipToPolygon(p1, p2, p3, p1)
                .execute();

        List<Suggestion> suggestions = autosuggest.getSuggestions();

        boolean found = false;
        for (Suggestion s : suggestions) {
            if (s.getWords().equalsIgnoreCase("index.home.raft")) {
                found = true;
            }
        }

        assertTrue("Can't find index.home.raft", found);
    }
}
