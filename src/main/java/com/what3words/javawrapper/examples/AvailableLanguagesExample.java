package com.what3words.javawrapper.examples;

import com.what3words.javawrapper.What3WordsV3;
import com.what3words.javawrapper.response.APIResponse.What3WordsError;
import com.what3words.javawrapper.response.AvailableLanguages;

public class AvailableLanguagesExample {
    public static void main(String[] args) {
        // For all requests a what3words API key is needed
        What3WordsV3 api = new What3WordsV3("what3words-api-key");

        AvailableLanguages languages = api.availableLanguages().execute();
        
        if (languages.isSuccessful()) { // the request was successful
            System.out.println("Languages: " + languages);
        } else {
            What3WordsError error = languages.getError();
            
            if (error == What3WordsError.INTERNAL_SERVER_ERROR) { // Server Error
                System.out.println("InternalServerError: " + error.getMessage());

            } else if (error == What3WordsError.NETWORK_ERROR) { // Network Error
                System.out.println("NetworkError: " + error.getMessage());

            } else {
                System.out.println(error + ": " + error.getMessage());

            }
        }
    }
}