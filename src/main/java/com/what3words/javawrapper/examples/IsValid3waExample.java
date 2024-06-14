package com.what3words.javawrapper.examples;

import com.what3words.javawrapper.What3WordsV3;
import com.what3words.javawrapper.request.AutosuggestInputType;
import com.what3words.javawrapper.request.AutosuggestOptions;
import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.APIResponse.What3WordsError;
import com.what3words.javawrapper.response.Autosuggest;

public class IsValid3waExample {
    public static void main(String[] args) {
        // For all requests a what3words API key is needed
        What3WordsV3 api = new What3WordsV3("what3words-api-key");

        Boolean valid1 = api.isValid3wa("filled.count.soap");
        if (valid1) {
            System.out.println("filled.count.soap is a valid what3words address");
        } else {
            System.out.println("filled.count.soap is a invalid what3words address");
        }

        Boolean valid2 = api.isValid3wa("filled.count.so");
        if (valid2) {
            System.out.println("filled.count.so is a valid what3words address");
        } else {
            System.out.println("filled.count.so is a invalid what3words address");
        }
    }
}