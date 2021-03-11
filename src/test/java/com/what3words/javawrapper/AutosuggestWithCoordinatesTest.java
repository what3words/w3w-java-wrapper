package com.what3words.javawrapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.AutosuggestWithCoordinates;
import com.what3words.javawrapper.response.SuggestionWithCoordinates;

public class AutosuggestWithCoordinatesTest {
	What3WordsV3 api = new What3WordsV3(System.getenv("W3W_API_KEY"));

	@Test
	public void testAutosuggestWithCoordinates() {

		/** autosuggest with coordinates, limiting the number of results to three */
		AutosuggestWithCoordinates autosuggest = api.autosuggestWithCoordinates("filled.count.soap").nResults(3)
				.focus(new Coordinates(51, 1)).execute();

		assertTrue(autosuggest.isSuccessful());

		SuggestionWithCoordinates suggestionsWithCoordinates = autosuggest.getSuggestions().get(0);

		assertEquals(suggestionsWithCoordinates.getCoordinates().getLat(), 51.520847, 0);
		assertEquals(suggestionsWithCoordinates.getCoordinates().getLng(), -0.195521, 0);

	}

}