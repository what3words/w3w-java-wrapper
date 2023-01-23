package com.what3words.javawrapper;

import com.what3words.javawrapper.request.*;
import com.what3words.javawrapper.services.What3WordsV3Service;
import retrofit2.Retrofit;

/**
 * Instances of the What3WordsV3 class provide access to Version 3 of the what3words API.
 */

public interface What3WordsJavaWrapper {
    ConvertTo3WARequest.Builder convertTo3wa(Coordinates coordinates);
    ConvertToCoordinatesRequest.Builder convertToCoordinates(String words);
    AutosuggestRequest.Builder autosuggest(String input);
    AutosuggestWithCoordinatesRequest.Builder autosuggestWithCoordinates(String input);
    AutosuggestSelectionRequest.Builder autosuggestionSelection(String rawInput, String selection, int rank, SourceApi sourceApi);
    GridSectionRequest.Builder gridSection(BoundingBox boundingBox);
    GridSectionGeoJsonRequest.Builder gridSectionGeoJson(BoundingBox boundingBox);
    AvailableLanguagesRequest.Builder availableLanguages();
    Retrofit getRetrofitInstance();
    What3WordsV3Service what3words();
}
