package com.what3words.javawrapper.examples;

import com.what3words.javawrapper.What3WordsV3;
import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.APIResponse.What3WordsError;
import com.what3words.javawrapper.response.ConvertTo3WA;

public class ConvertTo3WAExample {
    public static void main(String[] args) {
        // For all requests a what3words API key is needed
        What3WordsV3 api = new What3WordsV3("what3words-api-key");
        
        // Create and execute a request to convert a coordinate to a 3 word address
        ConvertTo3WA words = api.convertTo3wa(new Coordinates(51.508344, -0.12549900))
                .language("en")
                .execute();
        
        if (words.isSuccessful()) { // the request was successful
            System.out.println("Words: " + words);
        } else {
            What3WordsError error = words.getError();

            if (error == What3WordsError.BAD_COORDINATES) { // the coordinates provided were bad
                System.out.println("BadCoordinates: " + error.getMessage());

            } else if (error == What3WordsError.BAD_LANGUAGE) { // the language provided was bad 
                System.out.println("BadLanguage: " + error.getMessage());

            } else if (error == What3WordsError.INTERNAL_SERVER_ERROR) { // Server Error
                System.out.println("InternalServerError: " + error.getMessage());

            } else if (error == What3WordsError.NETWORK_ERROR) { // Network Error
                System.out.println("NetworkError: " + error.getMessage());

            } else {
                System.out.println(error + " " + error.getMessage());

            }
        }
    }
}

