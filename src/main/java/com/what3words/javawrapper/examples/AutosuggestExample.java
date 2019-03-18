package com.what3words.javawrapper.examples;

import com.what3words.javawrapper.What3WordsV3;
import com.what3words.javawrapper.request.AutosuggestInputType;
import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.APIResponse.Error;
import com.what3words.javawrapper.response.Autosuggest;

public class AutosuggestExample {
    public static void main(String[] args) {
        // For all requests a what3words API key is needed
        What3WordsV3 api = new What3WordsV3("what3words-api-key");

        /** Vanilla autosuggest, limiting the number of results to three */
        Autosuggest autosuggest = api.autosuggest("filled.count.soap")
                .nResults(3)
                .execute();
        if (autosuggest.isSuccessful()) {
            System.out.println("Autosuggest: " + autosuggest);
        } else {
            Error errorEnum = autosuggest.getError();

            if (errorEnum == Error.BAD_N_RESULTS) { // The number of results provided was invalid
                System.out.println("BadNResults: " + errorEnum.getMessage());

            } else if (errorEnum == Error.INTERNAL_SERVER_ERROR) { // Server Error
                System.out.println("InternalServerError: " + errorEnum.getMessage());

            } else if (errorEnum == Error.NETWORK_ERROR) { // Network Error
                System.out.println("NetworkError: " + errorEnum.getMessage());

            } else {
                System.out.println(errorEnum + ": " + errorEnum.getMessage());

            }
        }
        
        /** autosuggest demonstrating clipping to polygon, circle, bounding box, and country */
        autosuggest = api.autosuggest("filled.count.soap")
                .clipToPolygon(new Coordinates(52.321911, 1.516113), new Coordinates(52.321911, -2.021484), new Coordinates(50.345460, -2.021484), new Coordinates(52.321911, 1.516113))
//                .clipToCircle(new Coordinates(51.520833, -0.195543), 10)
//                .clipToBoundingBox(new BoundingBox(new Coordinates(50.345460, -2.021484), new Coordinates(52.321911, 1.516113)))
//                .clipToCountry("fr", "de")
                .execute();
        if (autosuggest.isSuccessful()) {
            System.out.println("Autosuggest: " + autosuggest);
        } else {
            Error error = autosuggest.getError();

            if (error == Error.BAD_CLIP_TO_CIRCLE) { // The circle clip provided is not valid
                System.out.println("BadClipToCircle: " + error.getMessage());

            } else if (error == Error.BAD_CLIP_TO_BOUNDING_BOX) { // The bounding box clip provided is not valid
                System.out.println("BadClipToBoundingBox: " + error.getMessage());

            } else if (error == Error.BAD_CLIP_TO_COUNTRY) { // The country list provided is not valid
                System.out.println("BadClipToCountry: " + error.getMessage());

            } else if (error == Error.BAD_CLIP_TO_POLYGON) { // The polygon clip provided is not valid
                System.out.println("BadClipToPolygon: " + error.getMessage());

            } else if (error == Error.INTERNAL_SERVER_ERROR) { // Server Error
                System.out.println("InternalServerError: " + error.getMessage());

            } else if (error == Error.NETWORK_ERROR) { // Network Error
                System.out.println("NetworkError: " + error.getMessage());

            } else {
                System.out.println(error + ": " + error.getMessage());

            }
        }

        /** autosuggest with a focus, with that focus only applied to the first result */
        autosuggest = api.autosuggest("filled.count.soap")
                .focus(new Coordinates(51.520833, -0.195543))
                .nFocusResults(1)
                .nResults(3)
                .execute();
        if (autosuggest.isSuccessful()) {
            System.out.println("Autosuggest: " + autosuggest);
        } else {
            Error error = autosuggest.getError();

            if (error == Error.BAD_FOCUS) { // The focus provided is not valid
                System.out.println("BadFocus: " + error.getMessage());

            } else if (error == Error.BAD_N_FOCUS_RESULTS) { // The number of results to provide a focus to is not valid
                System.out.println("BadNFocusResults: " + error.getMessage());

            } else if (error == Error.INTERNAL_SERVER_ERROR) { // Server Error
                System.out.println("InternalServerError: " + error.getMessage());

            } else if (error == Error.NETWORK_ERROR) { // Network Error
                System.out.println("NetworkError: " + error.getMessage());

            } else {
                System.out.println(error + ": " + error.getMessage());

            }
        }

        /** autosuggest with an input type of VoCon Hybrid */
        autosuggest = api.autosuggest("{\"_isInGrammar\":\"yes\",\"_isSpeech\":\"yes\",\"_hypotheses\":[{\"_score\":342516,\"_startRule\":\"whatthreewordsgrammar#_main_\",\"_conf\":6546,\"_endTimeMs\":6360,\"_beginTimeMs\":1570,\"_lmScore\":300,\"_items\":[{\"_type\":\"terminal\",\"_score\":34225,\"_orthography\":\"tend\",\"_conf\":6964,\"_endTimeMs\":2250,\"_beginTimeMs\":1580},{\"_type\":\"terminal\",\"_score\":47670,\"_orthography\":\"artichokes\",\"_conf\":7176,\"_endTimeMs\":3180,\"_beginTimeMs\":2260},{\"_type\":\"terminal\",\"_score\":43800,\"_orthography\":\"poached\",\"_conf\":6181,\"_endTimeMs\":4060,\"_beginTimeMs\":3220}]},{\"_score\":342631,\"_startRule\":\"whatthreewordsgrammar#_main_\",\"_conf\":6498,\"_endTimeMs\":6360,\"_beginTimeMs\":1570,\"_lmScore\":300,\"_items\":[{\"_type\":\"terminal\",\"_score\":34340,\"_orthography\":\"tent\",\"_conf\":6772,\"_endTimeMs\":2250,\"_beginTimeMs\":1580},{\"_type\":\"terminal\",\"_score\":47670,\"_orthography\":\"artichokes\",\"_conf\":7176,\"_endTimeMs\":3180,\"_beginTimeMs\":2260},{\"_type\":\"terminal\",\"_score\":43800,\"_orthography\":\"poached\",\"_conf\":6181,\"_endTimeMs\":4060,\"_beginTimeMs\":3220}]},{\"_score\":342668,\"_startRule\":\"whatthreewordsgrammar#_main_\",\"_conf\":6474,\"_endTimeMs\":6360,\"_beginTimeMs\":1570,\"_lmScore\":300,\"_items\":[{\"_type\":\"terminal\",\"_score\":34225,\"_orthography\":\"tend\",\"_conf\":6964,\"_endTimeMs\":2250,\"_beginTimeMs\":1580},{\"_type\":\"terminal\",\"_score\":47670,\"_orthography\":\"artichokes\",\"_conf\":7176,\"_endTimeMs\":3180,\"_beginTimeMs\":2260},{\"_type\":\"terminal\",\"_score\":41696,\"_orthography\":\"perch\",\"_conf\":5950,\"_endTimeMs\":4020,\"_beginTimeMs\":3220}]},{\"_score\":342670,\"_startRule\":\"whatthreewordsgrammar#_main_\",\"_conf\":6474,\"_endTimeMs\":6360,\"_beginTimeMs\":1570,\"_lmScore\":300,\"_items\":[{\"_type\":\"terminal\",\"_score\":34379,\"_orthography\":\"tinge\",\"_conf\":6705,\"_endTimeMs\":2250,\"_beginTimeMs\":1580},{\"_type\":\"terminal\",\"_score\":47670,\"_orthography\":\"artichokes\",\"_conf\":7176,\"_endTimeMs\":3180,\"_beginTimeMs\":2260},{\"_type\":\"terminal\",\"_score\":43800,\"_orthography\":\"poached\",\"_conf\":6181,\"_endTimeMs\":4060,\"_beginTimeMs\":3220}]},{\"_score\":342783,\"_startRule\":\"whatthreewordsgrammar#_main_\",\"_conf\":6426,\"_endTimeMs\":6360,\"_beginTimeMs\":1570,\"_lmScore\":300,\"_items\":[{\"_type\":\"terminal\",\"_score\":34340,\"_orthography\":\"tent\",\"_conf\":6772,\"_endTimeMs\":2250,\"_beginTimeMs\":1580},{\"_type\":\"terminal\",\"_score\":47670,\"_orthography\":\"artichokes\",\"_conf\":7176,\"_endTimeMs\":3180,\"_beginTimeMs\":2260},{\"_type\":\"terminal\",\"_score\":41696,\"_orthography\":\"perch\",\"_conf\":5950,\"_endTimeMs\":4020,\"_beginTimeMs\":3220}]},{\"_score\":342822,\"_startRule\":\"whatthreewordsgrammar#_main_\",\"_conf\":6402,\"_endTimeMs\":6360,\"_beginTimeMs\":1570,\"_lmScore\":300,\"_items\":[{\"_type\":\"terminal\",\"_score\":34379,\"_orthography\":\"tinge\",\"_conf\":6705,\"_endTimeMs\":2250,\"_beginTimeMs\":1580},{\"_type\":\"terminal\",\"_score\":47670,\"_orthography\":\"artichokes\",\"_conf\":7176,\"_endTimeMs\":3180,\"_beginTimeMs\":2260},{\"_type\":\"terminal\",\"_score\":41696,\"_orthography\":\"perch\",\"_conf\":5950,\"_endTimeMs\":4020,\"_beginTimeMs\":3220}]}],\"_resultType\":\"NBest\"}")
                .inputType(AutosuggestInputType.VOCON_HYBRID)
                .language("en")
                .execute();
        if (autosuggest.isSuccessful()) {
            System.out.println("Autosuggest: " + autosuggest);
        } else {
            Error error = autosuggest.getError();

            if (error == Error.BAD_INPUT) { // The input is not valid, given the input type provided
                System.out.println("BadInput: " + error.getMessage());

            } else if (error == Error.BAD_INPUT_TYPE) { // The input type is not valid
                System.out.println("BadInputType: " + error.getMessage());

            } else if (error == Error.BAD_LANGUAGE) { // The provided language is not valid
                System.out.println("BadLanguage: " + error.getMessage());

            } else if (error == Error.INTERNAL_SERVER_ERROR) { // Server Error
                System.out.println("InternalServerError: " + error.getMessage());

            } else if (error == Error.NETWORK_ERROR) { // Network Error
                System.out.println("NetworkError: " + error.getMessage());

            } else {
                System.out.println(error + ": " + error.getMessage());

            }
        }
    }
}