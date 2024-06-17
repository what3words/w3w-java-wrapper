package com.what3words.javawrapper.examples;

import com.what3words.javawrapper.What3WordsV3;
import com.what3words.javawrapper.request.AutosuggestInputType;
import com.what3words.javawrapper.request.AutosuggestOptions;
import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.APIResponse.What3WordsError;
import com.what3words.javawrapper.response.Autosuggest;
import com.what3words.javawrapper.response.IsValid3waResponse;

public class IsValid3waExample {
    public static void main(String[] args) {
        // For all requests a what3words API key is needed
        What3WordsV3 api = new What3WordsV3("what3words-api-key");

        IsValid3waResponse response = api.isValid3wa("filled.count.soap");
        if (response.isSuccessful() && response.getIsValid()) {
            System.out.println("filled.count.soap is a valid what3words address");
        } else if (response.isSuccessful() && !response.getIsValid()) {
            System.out.println("filled.count.soap is a invalid what3words address");
        } else {
            System.out.println("isValid3wa error: " + response.getError().getKey() + " " +  response.getError().getMessage());
        }

        IsValid3waResponse response2 = api.isValid3wa("filled.count.so");
        if (response2.isSuccessful() && response2.getIsValid()) {
            System.out.println("filled.count.so is a valid what3words address");
        } else if (response2.isSuccessful() && !response2.getIsValid()) {
            System.out.println("filled.count.so is a invalid what3words address");
        } else {
            System.out.println("isValid3wa error: " + response2.getError().getKey() + " " + response2.getError().getMessage());
        }
    }
}