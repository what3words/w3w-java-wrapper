package com.what3words.javawrapper.examples;

import com.what3words.javawrapper.What3WordsV3;
import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.APIResponse.What3WordsError;
import com.what3words.javawrapper.response.AutosuggestWithCoordinates;

public class AutosuggestWithCoordinatesExample {
	public static void main(String[] args) {
		// For all requests a what3words API key is needed
		What3WordsV3 api = new What3WordsV3("what3words-api-key");

		/** Vanilla autosuggest, limiting the number of results to three */
		AutosuggestWithCoordinates autosuggest = api.autosuggestWithCoordinates("filled.count.soap").nResults(3).focus(new Coordinates(51, 1))
				.execute();
		if (autosuggest.isSuccessful()) {
			System.out.println("Autosuggest: " + autosuggest);
		} else {
			What3WordsError errorEnum = autosuggest.getError();

			if (errorEnum == What3WordsError.BAD_N_RESULTS) { // The number of results provided was invalid
				System.out.println("BadNResults: " + errorEnum.getMessage());

			} else if (errorEnum == What3WordsError.INTERNAL_SERVER_ERROR) { // Server Error
				System.out.println("InternalServerError: " + errorEnum.getMessage());

			} else if (errorEnum == What3WordsError.NETWORK_ERROR) { // Network Error
				System.out.println("NetworkError: " + errorEnum.getMessage());

			} else {
				System.out.println(errorEnum + ": " + errorEnum.getMessage());

			}
		}

	}

}