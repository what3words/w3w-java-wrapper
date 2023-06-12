package com.what3words.javawrapper;

import com.what3words.javawrapper.response.AvailableLanguages;
import com.what3words.javawrapper.response.Language;
import com.what3words.javawrapper.response.Suggestion;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class AvailableLanguagesTest {
    What3WordsV3 api = new What3WordsV3(System.getenv("PROD_API_KEY"));

    @Test
    public void validAvailableLanguagesTest() {
        AvailableLanguages response = api.availableLanguages().execute();

        assertTrue(response.isSuccessful());
        assertTrue(response.getLanguages().size() > 0);

        boolean found = false;
        for (Language l : response.getLanguages()) {
            if (l.getCode().equalsIgnoreCase("mn") && l.getLocales() != null && !l.getLocales().isEmpty()) {
                found = true;
            }
            if (l.getCode().equalsIgnoreCase("zh") && l.getLocales() != null && !l.getLocales().isEmpty()) {
                found = true;
            }
        }

        assertTrue("Failed to find locale list for zh or mn", found);
    }
}
