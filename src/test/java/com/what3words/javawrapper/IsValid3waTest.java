package com.what3words.javawrapper;

import org.junit.Test;

import static org.junit.Assert.*;

public class IsValid3waTest {
    What3WordsV3 api = new What3WordsV3(System.getenv("PROD_API_KEY"));

    @Test
    public void testIsValid3waValidWords() {
        Boolean isValid = api.isValid3wa("filled.count.soap");
        assertTrue(isValid);
    }

    @Test
    public void testIsValid3waInvalidWords() {
        Boolean isValid = api.isValid3wa("filled.count.sos");
        assertFalse(isValid);
    }
}