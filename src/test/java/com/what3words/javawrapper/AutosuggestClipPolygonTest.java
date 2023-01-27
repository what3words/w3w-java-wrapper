package com.what3words.javawrapper;

import com.what3words.javawrapper.request.AutosuggestOptions;
import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.APIResponse;
import com.what3words.javawrapper.response.APIResponse.What3WordsError;
import com.what3words.javawrapper.response.Autosuggest;
import com.what3words.javawrapper.response.Suggestion;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AutosuggestClipPolygonTest {
    What3WordsV3 api = new What3WordsV3(System.getenv("PROD_API_KEY"));
    
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
    public void testPolygonClipWithOptions() {
        // Polygon must have at least 4 entries
        AutosuggestOptions options = new AutosuggestOptions();
        Coordinates p1 = new Coordinates(51, -1);
        Coordinates p2 = new Coordinates(53, 0);
        Coordinates p3 = new Coordinates(51, 1);
        options.setClipToPolygon(Arrays.asList(p1,p2,p3, p1));
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .options(options)
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
        
        What3WordsError error = autosuggest.getError();

        assertEquals(APIResponse.What3WordsError.BAD_CLIP_TO_POLYGON, error);
    }

    @Test
    public void testPolygonClipWithTooFewPointsWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        // Polygon must have at least 4 entries
        Coordinates p1 = new Coordinates(51, -1);
        Coordinates p2 = new Coordinates(53, 0);
        Coordinates p3 = new Coordinates(51, 1);
        options.setClipToPolygon(Arrays.asList(p1,p2,p3));


        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .options(options)
                .execute();

        What3WordsError error = autosuggest.getError();

        assertEquals(APIResponse.What3WordsError.BAD_CLIP_TO_POLYGON, error);
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

    @Test
    public void testPolygonClipWithHugeLongitudeWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        //Polygon must have at least 4 entries
        Coordinates p1 = new Coordinates(51, -1-180);
        Coordinates p2 = new Coordinates(53, 0);
        Coordinates p3 = new Coordinates(51, 1+180);
        options.setClipToPolygon(Arrays.asList(p1,p2,p3,p1));

        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .options(options)
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
