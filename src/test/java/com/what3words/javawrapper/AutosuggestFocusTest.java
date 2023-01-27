package com.what3words.javawrapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.what3words.javawrapper.request.AutosuggestOptions;
import org.junit.Test;

import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.APIResponse;
import com.what3words.javawrapper.response.Autosuggest;
import com.what3words.javawrapper.response.Suggestion;
import com.what3words.javawrapper.response.APIResponse.What3WordsError;

public class AutosuggestFocusTest {
    What3WordsV3 api = new What3WordsV3(System.getenv("PROD_API_KEY"));

    @Test
    public void testValidFocus() {
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .focus(new Coordinates(51.2, 0.2))
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
    public void testValidFocusWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        options.setFocus(new Coordinates(51.2, 0.2));
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

        assertTrue("Failed to find index.home.raft", found);
    }

    @Test
    public void testFocusLatitudeTooBig() {
        Autosuggest response = api.autosuggest("index.home.ra")
                .focus(new Coordinates(151.2, 0.2))
                .execute();

        What3WordsError error = response.getError();

        assertEquals(APIResponse.What3WordsError.BAD_FOCUS, error);
    }

    @Test
    public void testFocusLatitudeTooBigWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        options.setFocus(new Coordinates(151.2, 0.2));
        Autosuggest response = api.autosuggest("index.home.ra")
                .options(options)
                .execute();

        What3WordsError error = response.getError();

        assertEquals(APIResponse.What3WordsError.BAD_FOCUS, error);
    }

    @Test
    public void testFocusLatitudeTooSmall() {
        Autosuggest response = api.autosuggest("index.home.ra")
                .focus(new Coordinates(-151.2, 0.2))
                .execute();

        What3WordsError error = response.getError();

        assertEquals(APIResponse.What3WordsError.BAD_FOCUS, error);
    }

    @Test
    public void testFocusLatitudeTooSmallWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        options.setFocus(new Coordinates(-151.2, 0.2));
        Autosuggest response = api.autosuggest("index.home.ra")
                .options(options)
                .execute();

        What3WordsError error = response.getError();

        assertEquals(APIResponse.What3WordsError.BAD_FOCUS, error);
    }

    @Test
    public void testFocusBigLongitude() {
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .focus(new Coordinates(51.2, 360.2))
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
    public void testFocusBigLongitudeWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        options.setFocus(new Coordinates(51.2, 360.2));
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

        assertTrue("Failed to find index.home.raft", found);
    }

    @Test
    public void testFocusSmallLongitude() {
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .focus(new Coordinates(51.2, -360))
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
    public void testFocusSmallLongitudeWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        options.setFocus(new Coordinates(51.2, -360));
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

        assertTrue("Failed to find index.home.raft", found);
    }
}
