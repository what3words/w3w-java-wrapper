package com.what3words.javawrapper.response;

import com.google.gson.GsonBuilder;

public class ConvertToCoordinates extends Response<ConvertToCoordinates> {
    private String country;
    private Square square;
    private String nearestPlace;
    private Coordinates coordinates;
    private String words;
    private String language;
    private String map;

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
    
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
