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
    
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Square getSquare() {
		return square;
	}
	public void setSquare(Square square) {
		this.square = square;
	}
	public String getNearestPlace() {
		return nearestPlace;
	}
	public void setNearestPlace(String nearestPlace) {
		this.nearestPlace = nearestPlace;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
	public Integer getDistanceToFocusKm() {
		return distanceToFocusKm;
	}
	public void setDistanceToFocusKm(Integer distanceToFocusKm) {
		this.distanceToFocusKm = distanceToFocusKm;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
    
}
