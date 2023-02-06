package com.what3words.javawrapper;

import com.what3words.javawrapper.response.AvailableLanguages;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AvailableLanguagesTest {
    What3WordsV3 api = new What3WordsV3(System.getenv("PROD_API_KEY"));

    @Test
    public void validAvailableLanguagesTest() {
        AvailableLanguages response = api.availableLanguages().execute();

        assertTrue(response.isSuccessful());
        assertTrue(response.getLanguages().size() > 0);
    }
}
