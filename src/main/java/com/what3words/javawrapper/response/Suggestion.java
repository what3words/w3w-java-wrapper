package com.what3words.javawrapper.response;

public class Suggestion {
    private String country;
    private String nearestPlace;
    private String words;
    private Integer distanceToFocusKm;
    private int rank;
    private String language;

    public String getCountry() {
        return country;
    }

    public String getNearestPlace() {
        return nearestPlace;
    }

    public String getWords() {
        return words;
    }

    public Integer getDistanceToFocusKm() {
        return distanceToFocusKm;
    }

    public int getRank() {
        return rank;
    }

    public String getLanguage() {
        return language;
    }
}