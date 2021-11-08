package com.what3words.javawrapper;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.what3words.javawrapper.request.AutosuggestOptions;
import org.junit.Test;

import com.what3words.javawrapper.request.BoundingBox;
import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.APIResponse;
import com.what3words.javawrapper.response.APIResponse.What3WordsError;
import com.what3words.javawrapper.response.Autosuggest;
import com.what3words.javawrapper.response.Suggestion;

public class AutosuggestClipTest {
    What3WordsV3 api = new What3WordsV3(System.getenv("W3W_API_KEY"));

    @Test
    public void testSimpleCircleClip() {
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .clipToCircle(new Coordinates(-90.000000,360.0), 100)
                .execute();

        assertNotNull(autosuggest);
    }

    @Test
    public void testSimpleCircleClipWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        options.setClipToCircle(new Coordinates(-90.000000,360.0));
        options.setClipToCircleRadius(100.0);
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .options(options)
                .execute();

        assertNotNull(autosuggest);
    }

    @Test
    public void testSimpleCircleClipLatCannotWrap() {
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .clipToCircle(new Coordinates(-91.000000,360.0), 100)
                .execute();
        
        What3WordsError error = autosuggest.getError();
        
        assertEquals(APIResponse.What3WordsError.BAD_CLIP_TO_CIRCLE, error);
    }

    @Test
    public void testSimpleCircleClipLatCannotWrapWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        options.setClipToCircle(new Coordinates(-91.000000,360.0));
        options.setClipToCircleRadius(100.0);
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .options(options)
                .execute();

        What3WordsError error = autosuggest.getError();

        assertEquals(APIResponse.What3WordsError.BAD_CLIP_TO_CIRCLE, error);
    }

    @Test
    public void testSimpleCircleClipLatBigDistance() {
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .clipToCircle(new Coordinates(0.000000,0.0), 10000000)
                .execute();

        assertNotNull(autosuggest);

    }

    @Test
    public void testSimpleCircleClipLatBigDistanceWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        options.setClipToCircle(new Coordinates(0.000000,0.0));
        options.setClipToCircleRadius(10000000.0);
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .options(options)
                .execute();

        assertNotNull(autosuggest);
    }

    @Test
    public void testSimpleCircleClipLatNegativeDistance() {
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .clipToCircle(new Coordinates(0.000000,0.0), -1.0)
                .execute();

        assertNotNull(autosuggest);
    }

    @Test
    public void testSimpleCircleClipLatNegativeDistanceWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        options.setClipToCircle(new Coordinates(0.000000,0.0));
        options.setClipToCircleRadius(-1.0);
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .options(options)
                .execute();

        assertNotNull(autosuggest);
    }

    @Test
    public void testBoundingBox() {
        Coordinates sw = new Coordinates(50, -5);
        Coordinates ne = new Coordinates(53, 2);
        BoundingBox bbox = new BoundingBox(sw, ne);

        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .clipToBoundingBox(bbox)
                .execute();

        List<Suggestion> suggestions = autosuggest.getSuggestions();
        boolean found = false;
        for (Suggestion s : suggestions) {
            if (s.getWords().equalsIgnoreCase("index.home.raft")) {
                found = true;
            }
        }
        assertTrue("Failed to find index.home.raft", found);
    }

    @Test
    public void testBoundingBoxWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        Coordinates sw = new Coordinates(50, -5);
        Coordinates ne = new Coordinates(53, 2);
        BoundingBox bbox = new BoundingBox(sw, ne);
        options.setClipToBoundingBox(bbox);

        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .options(options)
                .execute();

        List<Suggestion> suggestions = autosuggest.getSuggestions();
        boolean found = false;
        for (Suggestion s : suggestions) {
            if (s.getWords().equalsIgnoreCase("index.home.raft")) {
                found = true;
            }
        }
        assertTrue("Failed to find index.home.raft", found);
    }

    @Test
    public void testBoundingBoxInfinitelySmall() {
        Coordinates sw = new Coordinates(50, -5);
        Coordinates ne = new Coordinates(50, -5);

        BoundingBox bbox = new BoundingBox(sw, ne);
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .clipToBoundingBox(bbox)
                .execute();
        List<Suggestion> suggestions = autosuggest.getSuggestions();
        boolean found = false;
        for (Suggestion s : suggestions) {
            if (s.getWords().equalsIgnoreCase("index.home.raft")) {
                found = true;
            }
        }

        assertTrue("Found index.home.raft", !found);
    }

    @Test
    public void testBoundingBoxInfinitelySmallWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        Coordinates sw = new Coordinates(50, -5);
        Coordinates ne = new Coordinates(50, -5);
        BoundingBox bbox = new BoundingBox(sw, ne);
        options.setClipToBoundingBox(bbox);

        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .options(options)
                .execute();
        List<Suggestion> suggestions = autosuggest.getSuggestions();
        boolean found = false;
        for (Suggestion s : suggestions) {
            if (s.getWords().equalsIgnoreCase("index.home.raft")) {
                found = true;
            }
        }

        assertTrue("Found index.home.raft", !found);
    }

    @Test
    public void testBoundingBoxLngWraps() {
        Coordinates sw = new Coordinates(50, -5);
        Coordinates ne = new Coordinates(53, 3544);

        BoundingBox bbox = new BoundingBox(sw, ne);
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .clipToBoundingBox(bbox)
                .execute();
        List<Suggestion> suggestions = autosuggest.getSuggestions();
        boolean found = false;
        for (Suggestion s : suggestions) {
            if (s.getWords().equalsIgnoreCase("index.home.raft")) {
                found = true;
            }
        }

        assertTrue("Failed to find index.home.raft", found);
    }

    @Test
    public void testBoundingBoxLngWrapsWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        Coordinates sw = new Coordinates(50, -5);
        Coordinates ne = new Coordinates(53, 3544);
        BoundingBox bbox = new BoundingBox(sw, ne);
        options.setClipToBoundingBox(bbox);

        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .options(options)
                .execute();
        List<Suggestion> suggestions = autosuggest.getSuggestions();
        boolean found = false;
        for (Suggestion s : suggestions) {
            if (s.getWords().equalsIgnoreCase("index.home.raft")) {
                found = true;
            }
        }

        assertTrue("Failed to find index.home.raft", found);
    }

    @Test
    public void testBoundingBoxThatWrapsAroundWorldButExcludesLondon() {
        Coordinates sw = new Coordinates(50, 2);
        Coordinates ne = new Coordinates(53, -5 + 360);

        BoundingBox bbox = new BoundingBox(sw, ne);
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .clipToBoundingBox(bbox)
                .execute();
        List<Suggestion> suggestions = autosuggest.getSuggestions();
        boolean found = false;
        for (Suggestion s : suggestions) {
            if (s.getWords().equalsIgnoreCase("index.home.raft")) {
                found = true;
            }
        }

        assertTrue("Found index.home.raft", !found);
    }

    @Test
    public void testBoundingBoxThatWrapsAroundWorldButExcludesLondonWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        Coordinates sw = new Coordinates(50, 2);
        Coordinates ne = new Coordinates(53, -5 + 360);
        BoundingBox bbox = new BoundingBox(sw, ne);
        options.setClipToBoundingBox(bbox);

        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .options(options)
                .execute();
        List<Suggestion> suggestions = autosuggest.getSuggestions();
        boolean found = false;
        for (Suggestion s : suggestions) {
            if (s.getWords().equalsIgnoreCase("index.home.raft")) {
                found = true;
            }
        }

        assertTrue("Found index.home.raft", !found);
    }

    @Test//(expected = IllegalArgumentException.class)
    public void testBoundingBoxThatWrapsAroundPolesButExcludesLondon() {
        Coordinates sw = new Coordinates(53, -5);
        Coordinates ne = new Coordinates(50+180, 2);

        BoundingBox bbox = new BoundingBox(sw, ne);
        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .clipToBoundingBox(bbox)
                .execute();

        What3WordsError error = autosuggest.getError();

        assertEquals(APIResponse.What3WordsError.BAD_CLIP_TO_BOUNDING_BOX, error);
    }

    @Test//(expected = IllegalArgumentException.class)
    public void testBoundingBoxThatWrapsAroundPolesButExcludesLondonWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        Coordinates sw = new Coordinates(53, -5);
        Coordinates ne = new Coordinates(50+180, 2);
        BoundingBox bbox = new BoundingBox(sw, ne);
        options.setClipToBoundingBox(bbox);

        Autosuggest autosuggest = api.autosuggest("index.home.ra")
                .options(options)
                .execute();

        What3WordsError error = autosuggest.getError();

        assertEquals(APIResponse.What3WordsError.BAD_CLIP_TO_BOUNDING_BOX, error);
    }

    @Test
    public void clipToCountryThatDoesNotExist() {
        Autosuggest autosuggest = api.autosuggest("index.home.raf")
                .clipToCountry("ZX")
                .execute();
        
        List<Suggestion> suggestions = autosuggest.getSuggestions();
        
        boolean found = false;
        for (Suggestion s : suggestions) {
            System.out.println(s.getWords());
            if (s.getWords().equalsIgnoreCase("index.home.raft")) {
                found = true;
            }
        }

        assertTrue("Found index.home.raft", !found);
    }

    @Test
    public void clipToCountryThatDoesNotExistWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        options.setClipToCountry(Collections.singletonList("ZX"));
        Autosuggest autosuggest = api.autosuggest("index.home.raf")
                .options(options)
                .execute();

        List<Suggestion> suggestions = autosuggest.getSuggestions();

        boolean found = false;
        for (Suggestion s : suggestions) {
            System.out.println(s.getWords());
            if (s.getWords().equalsIgnoreCase("index.home.raft")) {
                found = true;
            }
        }

        assertTrue("Found index.home.raft", !found);
    }
    
    @Test
    public void clipToInvalidCountry() {
        Autosuggest response = api.autosuggest("index.home.raf")
                .clipToCountry("ZXC")
                .execute();
        
        What3WordsError error = response.getError();

        assertEquals(APIResponse.What3WordsError.BAD_CLIP_TO_COUNTRY, error);
    }

    @Test
    public void clipToInvalidCountryWithOptions() {
        AutosuggestOptions options = new AutosuggestOptions();
        options.setClipToCountry(Collections.singletonList("ZXC"));
        Autosuggest response = api.autosuggest("index.home.raf")
                .options(options)
                .execute();

        What3WordsError error = response.getError();

        assertEquals(APIResponse.What3WordsError.BAD_CLIP_TO_COUNTRY, error);
    }
}
