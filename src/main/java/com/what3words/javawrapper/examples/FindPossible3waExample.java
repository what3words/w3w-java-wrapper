package com.what3words.javawrapper.examples;

import com.what3words.javawrapper.What3WordsV3;

import java.util.List;

public class FindPossible3waExample {
    public static void main(String[] args) {
        // Example texts
        String[] texts = {
                "Please leave by my porch at filled.count.soap",
                "Please leave by my porch at filled.count.soap or deed.tulip.judge",
                "Please leave by my porch at"
        };

        // Check each text for possible what3words addresses
        for (String text : texts) {
            List<String> possibleAddresses = What3WordsV3.findPossible3wa(text);
            System.out.println("Possible what3words addresses in '" + text + "': " + possibleAddresses);
        }
    }
}
