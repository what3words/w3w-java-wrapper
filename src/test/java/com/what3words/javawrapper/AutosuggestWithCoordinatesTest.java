package com.what3words.javawrapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.what3words.javawrapper.request.AutosuggestOptions;
import com.what3words.javawrapper.request.SourceApi;
import com.what3words.javawrapper.response.AutosuggestSelection;
import org.junit.Test;

import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.AutosuggestWithCoordinates;
import com.what3words.javawrapper.response.SuggestionWithCoordinates;

public class AutosuggestWithCoordinatesTest {
    What3WordsV3 api = new What3WordsV3(System.getenv("PROD_API_KEY"));

    @Test
    public void testAutosuggestWithCoordinates() {

        /** autosuggest with coordinates, limiting the number of results to three */
        AutosuggestWithCoordinates autosuggest = api.autosuggestWithCoordinates("filled.count.soap").nResults(3)
                .focus(new Coordinates(51, 1)).execute();

        assertTrue(autosuggest.isSuccessful());

        SuggestionWithCoordinates suggestionsWithCoordinates = autosuggest.getSuggestions().get(0);

        assertEquals(suggestionsWithCoordinates.getCoordinates().getLat(), 51.520847, 0);
        assertEquals(suggestionsWithCoordinates.getCoordinates().getLng(), -0.195521, 0);

        AutosuggestSelection selection = api.autosuggestionSelection("filled.count.soap", suggestionsWithCoordinates.getWords(), suggestionsWithCoordinates.getRank(), SourceApi.TEXT).focus(new Coordinates(51, 1)).execute();
        assertTrue(selection.isSuccessful());
    }

    @Test
    public void testAutosuggestWithCoordinatesAndOptions() {

        /** autosuggest with coordinates, limiting the number of results to three */
        AutosuggestOptions options = new AutosuggestOptions();
        options.setFocus(new Coordinates(51, 1));
        AutosuggestWithCoordinates autosuggest = api.autosuggestWithCoordinates("filled.count.soap").nResults(3)
                .options(options).execute();

        assertTrue(autosuggest.isSuccessful());

        SuggestionWithCoordinates suggestionsWithCoordinates = autosuggest.getSuggestions().get(0);

        assertEquals(suggestionsWithCoordinates.getCoordinates().getLat(), 51.520847, 0);
        assertEquals(suggestionsWithCoordinates.getCoordinates().getLng(), -0.195521, 0);

        AutosuggestSelection selection = api.autosuggestionSelection("filled.count.soap", suggestionsWithCoordinates.getWords(), suggestionsWithCoordinates.getRank(), SourceApi.TEXT).focus(new Coordinates(51, 1)).execute();
        assertTrue(selection.isSuccessful());
    }

   // @Test
    public void testAutosuggestWithCoordinatesWithLocale() {

        /** autosuggest with coordinates, limiting the number of results to three */
        AutosuggestOptions options = new AutosuggestOptions();
        options.setFocus(new Coordinates(51, 1));
        options.setLocale("mn_la");
        AutosuggestWithCoordinates autosuggest = api.autosuggestWithCoordinates("a.a.a").nResults(3)
                .options(options).execute();

        assertTrue(autosuggest.isSuccessful());

        SuggestionWithCoordinates suggestionsWithCoordinates = autosuggest.getSuggestions().get(0);

        assertEquals(51.101296, suggestionsWithCoordinates.getCoordinates().getLat(), 0);
        assertEquals(0.269781, suggestionsWithCoordinates.getCoordinates().getLng(),  0);
        assertEquals("mn_la", suggestionsWithCoordinates.getLocale());

        AutosuggestSelection selection = api.autosuggestionSelection("filled.count.soap", suggestionsWithCoordinates.getWords(), suggestionsWithCoordinates.getRank(), SourceApi.TEXT).focus(new Coordinates(51, 1)).execute();
        assertTrue(selection.isSuccessful());
    }
}