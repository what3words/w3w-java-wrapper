package com.what3words.javawrapper.response;

public class SuggestionWithCoordinates {
    private String country;
    private Square square;
    private String nearestPlace;
    private Coordinates coordinates;
    private String words;
    private String language;
    private String map;
    private Integer distanceToFocusKm;
    private int rank;

    public SuggestionWithCoordinates(Suggestion suggestion) {
        country = suggestion.getCountry();
        nearestPlace = suggestion.getNearestPlace();
        words = suggestion.getWords();
        language = suggestion.getLanguage();
        distanceToFocusKm = suggestion.getDistanceToFocusKm();
        rank = suggestion.getRank();
    }

    public SuggestionWithCoordinates(Suggestion suggestion, Coordinates coordinates) {
        country = suggestion.getCountry();
        nearestPlace = suggestion.getNearestPlace();
        words = suggestion.getWords();
        language = suggestion.getLanguage();
        distanceToFocusKm = suggestion.getDistanceToFocusKm();
        rank = suggestion.getRank();
        this.coordinates = coordinates;
    }

    public SuggestionWithCoordinates(Suggestion suggestion, double lat, double lng) {
        country = suggestion.getCountry();
        nearestPlace = suggestion.getNearestPlace();
        words = suggestion.getWords();
        language = suggestion.getLanguage();
        distanceToFocusKm = suggestion.getDistanceToFocusKm();
        rank = suggestion.getRank();
        this.coordinates = new Coordinates(lat, lng);
    }

    public SuggestionWithCoordinates(Suggestion suggestion, ConvertToCoordinates convertToCoordinates) {
        country = suggestion.getCountry();
        nearestPlace = suggestion.getNearestPlace();
        words = suggestion.getWords();
        language = suggestion.getLanguage();
        distanceToFocusKm = suggestion.getDistanceToFocusKm();
        rank = suggestion.getRank();
        coordinates = convertToCoordinates.getCoordinates();
        map = convertToCoordinates.getMap();
        square = convertToCoordinates.getSquare();
    }

    public SuggestionWithCoordinates(Suggestion suggestion, ConvertTo3WA convertTo3WA) {
        country = suggestion.getCountry();
        nearestPlace = suggestion.getNearestPlace();
        words = suggestion.getWords();
        language = suggestion.getLanguage();
        distanceToFocusKm = suggestion.getDistanceToFocusKm();
        rank = suggestion.getRank();
        coordinates = convertTo3WA.getCoordinates();
        map = convertTo3WA.getMap();
        square = convertTo3WA.getSquare();
    }

    public String getCountry() {
        return country;
    }

    public Square getSquare() {
        return square;
    }

    public String getNearestPlace() {
        return nearestPlace;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getWords() {
        return words;
    }

    public String getLanguage() {
        return language;
    }

    public String getMap() {
        return map;
    }

    public Integer getDistanceToFocusKm() {
        return distanceToFocusKm;
    }

    public int getRank() {
        return rank;
    }
}
