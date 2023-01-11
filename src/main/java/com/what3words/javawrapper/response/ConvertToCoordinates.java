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

    public ConvertToCoordinates(String country, Square square, String nearestPlace, Coordinates coordinates, String words, String language, String map) {
        this.country = country;
        this.square = square;
        this.nearestPlace = nearestPlace;
        this.coordinates = coordinates;
        this.words = words;
        this.language = language;
        this.map = map;
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
    
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
