package com.what3words.javawrapper;

import java.util.List;

import com.what3words.javawrapper.request.AutosuggestOptions;
import com.what3words.javawrapper.request.SourceApi;
import com.what3words.javawrapper.response.*;
import org.junit.Test;

import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.APIResponse.What3WordsError;

import static org.junit.Assert.*;

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
    public void testValidLocale() {
        AutosuggestOptions options = new AutosuggestOptions();
        options.setFocus(new Coordinates(51, 1));
        options.setLocale("mn_la");
        Autosuggest autosuggest = api.autosuggest("a.a.a").nResults(3)
                .options(options).execute();

        assertTrue(autosuggest.isSuccessful());

        Suggestion suggestion = autosuggest.getSuggestions().get(0);

        assertEquals("mn_la", suggestion.getLocale());
    }

    @Test
    public void testInvalidLocale() {
        AutosuggestOptions options = new AutosuggestOptions();
        options.setFocus(new Coordinates(51, 1));
        options.setLocale("mn_las");
        Autosuggest autosuggest = api.autosuggest("a.a.a").nResults(3)
                .options(options).execute();

        assertFalse(autosuggest.isSuccessful());

        What3WordsError error = autosuggest.getError();

        assertEquals(What3WordsError.BAD_LANGUAGE, error);
    }

    @Test
    public void testInvalidLocaleButValidLanguage() {
        AutosuggestOptions options = new AutosuggestOptions();
        options.setFocus(new Coordinates(51, 1));
        options.setLanguage("mn");
        options.setLocale("mn_las");
        Autosuggest autosuggest = api.autosuggest("a.a.a").nResults(3)
                .options(options).execute();

        assertFalse(autosuggest.isSuccessful());

        What3WordsError error = autosuggest.getError();

        assertEquals(What3WordsError.BAD_LANGUAGE, error);
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
