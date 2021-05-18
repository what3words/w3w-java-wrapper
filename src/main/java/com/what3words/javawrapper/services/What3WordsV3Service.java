package com.what3words.javawrapper.services;

import com.what3words.javawrapper.response.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface What3WordsV3Service {
    @GET("convert-to-3wa")
    Call<ConvertTo3WA> convertTo3wa(@Query("coordinates") String coordinates, @Query("language") String language);
    
    @GET("convert-to-coordinates")
    Call<ConvertToCoordinates> convertToCoordinates(@Query("words") String addr);
    
    @GET("autosuggest")
    Call<Autosuggest> autosuggest(
            @Query("input") String input, 
            @Query("n-results") String nResults, 
            @Query("focus") String focus,
            @Query("n-focus-results") String nFocusResults,
            @Query("clip-to-country") String clipToCountry,
            @Query("clip-to-bounding-box") String clipToBoundingBox,
            @Query("clip-to-circle") String clipToCircle,
            @Query("clip-to-polygon") String clipToPolygon,
            @Query("input-type") String inputType,
            @Query("language") String lang,
            @Query("prefer-land") String preferLand
            );
    
    @GET("autosuggest-with-coordinates")
    Call<AutosuggestWithCoordinates> autosuggestWithCoordinates(
            @Query("input") String input, 
            @Query("n-results") String nResults, 
            @Query("focus") String focus,
            @Query("n-focus-results") String nFocusResults,
            @Query("clip-to-country") String clipToCountry,
            @Query("clip-to-bounding-box") String clipToBoundingBox,
            @Query("clip-to-circle") String clipToCircle,
            @Query("clip-to-polygon") String clipToPolygon,
            @Query("input-type") String inputType,
            @Query("language") String lang,
            @Query("prefer-land") String preferLand
            );

    @GET("autosuggest-selection")
    Call<Void> autosuggestSelection(
            @Query("raw-input") String rawInput,
            @Query("selection") String selection,
            @Query("rank") String rank,
            @Query("source-api") String sourceApi,
            @Query("n-results") String nResults,
            @Query("focus") String focus,
            @Query("n-focus-results") String nFocusResults,
            @Query("clip-to-country") String clipToCountry,
            @Query("clip-to-bounding-box") String clipToBoundingBox,
            @Query("clip-to-circle") String clipToCircle,
            @Query("clip-to-polygon") String clipToPolygon,
            @Query("input-type") String inputType,
            @Query("language") String lang,
            @Query("prefer-land") String preferLand
    );
    
    @GET("grid-section")
    Call<GridSection> gridSection(@Query("bounding-box") String bbox);
    
    @GET("available-languages")
    Call<AvailableLanguages> availableLanguages();
}