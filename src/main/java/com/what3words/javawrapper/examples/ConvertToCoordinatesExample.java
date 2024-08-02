package com.what3words.javawrapper.examples;

import com.what3words.javawrapper.What3WordsV3;
import com.what3words.javawrapper.response.APIResponse.What3WordsError;
import com.what3words.javawrapper.response.ConvertToCoordinates;

public class ConvertToCoordinatesExample {
    public static void main(String[] args) {
        // For all requests a what3words API key is needed
        What3WordsV3 api = new What3WordsV3("what3words-api-key");

        // Create and execute a request with the 3 word address such as "filled.count.soap"
        ConvertToCoordinates coordinates = api.convertToCoordinates("filled.count.soap").execute();

        if (coordinates.isSuccessful()) { // the request was successful
            System.out.println("Coordinates: " + coordinates);

        } else { // the request was not successful
            What3WordsError error = coordinates.getError();

            if (error == What3WordsError.BAD_WORDS) { // The three word address provided is invalid
                System.out.println("BadWords: " + error.getMessage());

            } else if (error == What3WordsError.INTERNAL_SERVER_ERROR) { // Server Error
                System.out.println("InternalServerError: " + error.getMessage());

            } else if (error == What3WordsError.NETWORK_ERROR) { // Network Error
                System.out.println("NetworkError: " + error.getMessage());

            } else if (error == What3WordsError.QUOTA_EXCEEDED) { // Network Error
                System.out.println("QuotaExceeded: " + error.getMessage());

            } else { // Unknown Error
                System.out.println(error + ": " + error.getMessage());

            }
        }
    }
}