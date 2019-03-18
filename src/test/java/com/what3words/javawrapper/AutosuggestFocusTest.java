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

public class AutosuggestFocusTest {
    What3WordsV3 api = new What3WordsV3("what3words-api-key");
    
    @Test
    public void testValidFocus() {
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .focus(new Coordinates(51.2,0.2))
                .execute();
        
        List<Suggestion> suggestions = autosuggest.getSuggestions();

        boolean found = false;
        for (Suggestion s : suggestions) {
            if (s.getWords().equalsIgnoreCase("index.home.raft")) {
                found = true;
            }
        }

        assertTrue("Failed to find index.home.raft", found);
    }

    @Test
    public void testFocusLatitudeTooBig() {
        Autosuggest response = api.autosuggest("index.home.ra")
                .focus(new Coordinates(151.2,0.2))
                .execute();
        
        Error error = response.getError();

        assertEquals(APIResponse.Error.BAD_FOCUS, error);
    }

    @Test
    public void testFocusLatitudeTooSmall() {
        Autosuggest response = api.autosuggest("index.home.ra")
                .focus(new Coordinates(-151.2,0.2))
                .execute();
        
        Error error = response.getError();

        assertEquals(APIResponse.Error.BAD_FOCUS, error);
    }

    @Test
    public void testFocusBigLongitude() {
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .focus(new Coordinates(51.2,360.2))
                .execute();
        
        List<Suggestion> suggestions = autosuggest.getSuggestions();

        boolean found = false;
        for (Suggestion s : suggestions) {
            if (s.getWords().equalsIgnoreCase("index.home.raft")) {
                found = true;
            }
        }

        assertTrue("Failed to find index.home.raft", found);
    }

    @Test
    public void testFocusSmallLongitude() {
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .focus(new Coordinates(51.2,-360))
                .execute();
        
        List<Suggestion> suggestions = autosuggest.getSuggestions();

        boolean found = false;
        for (Suggestion s : suggestions) {
            if (s.getWords().equalsIgnoreCase("index.home.raft")) {
                found = true;
            }
        }

        assertTrue("Failed to find index.home.raft", found);
    }
}
