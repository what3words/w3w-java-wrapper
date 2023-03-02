package com.what3words.javawrapper.response;

import java.io.Serializable;

public class Suggestion implements Serializable {
    private String country;
    private String nearestPlace;
    private String words;
    private Integer distanceToFocusKm;
    private int rank;
    private String language;

    public Suggestion(String words, String nearestPlace, String country, Integer distanceToFocusKm, int rank, String language) {
        this.words = words;
        this.nearestPlace = nearestPlace;
        this.country = country;
        this.distanceToFocusKm = distanceToFocusKm;
        this.rank = rank;
        this.language = language;
    }

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