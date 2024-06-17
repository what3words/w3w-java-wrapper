package com.what3words.javawrapper;

import com.what3words.javawrapper.response.APIResponse;
import com.what3words.javawrapper.response.IsValid3waResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class IsValid3waTest {
    What3WordsV3 api = new What3WordsV3(System.getenv("PROD_API_KEY"));

    @Test
    public void testIsValid3waValidWords() {
        IsValid3waResponse response = api.isValid3wa("filled.count.soap");
        assertTrue(response.isSuccessful());
        assertNull(response.getError());
        assertTrue(response.getIsValid());
    }

    @Test
    public void testIsValid3waInvalidWords() {
        IsValid3waResponse response = api.isValid3wa("filled.count.sos");
        assertTrue(response.isSuccessful());
        assertNull(response.getError());
        assertFalse(response.getIsValid());
    }

    @Test
    public void testIsValid3waApiKeyError() {
        What3WordsV3 errorApi = new What3WordsV3("wrong api key");
        IsValid3waResponse response = errorApi.isValid3wa("filled.count.soap");
        assertNull(response.getIsValid());
        assertFalse(response.isSuccessful());
        assertSame(APIResponse.What3WordsError.INVALID_KEY, response.getError());
    }
}