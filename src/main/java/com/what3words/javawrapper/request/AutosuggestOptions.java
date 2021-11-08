package com.what3words.javawrapper.request;

import java.util.List;

public class AutosuggestOptions {
    private Coordinates focus;
    private SourceApi source;
    private String language;
    private Integer nResults;
    private Integer nFocusResults;
    private List<String> clipToCountry;
    private Coordinates clipToCircle;
    private Double clipToCircleRadius;
    private BoundingBox clipToBoundingBox;
    private List<Coordinates> clipToPolygon;
    private AutosuggestInputType inputType;
    private Boolean preferLand;

    public Coordinates getFocus() {
        return this.focus;
    }

    public void setFocus(Coordinates coordinates) {
        this.focus = coordinates;
    }

    public final SourceApi getSource() {
        return this.source;
    }

    public final void setSource(SourceApi source) {
        this.source = source;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final void setLanguage(String language) {
        this.language = language;
    }

    public final Integer getNResults() {
        return this.nResults;
    }

    public final void setNResults(Integer n) {
        this.nResults = n;
    }

    public final Integer getNFocusResults() {
        return this.nFocusResults;
    }

    public final void setNFocusResults(Integer n) {
        this.nFocusResults = n;
    }

    public final List<String> getClipToCountry() {
        return this.clipToCountry;
    }

    public final void setClipToCountry(List<String> countries) {
        this.clipToCountry = countries;
    }

    public final Coordinates getClipToCircle() {
        return this.clipToCircle;
    }

    public final void setClipToCircle(Coordinates coordinates) {
        this.clipToCircle = coordinates;
    }

    public final Double getClipToCircleRadius() {
        return this.clipToCircleRadius;
    }

    public final void setClipToCircleRadius(Double radius) {
        this.clipToCircleRadius = radius;
    }

    public final BoundingBox getClipToBoundingBox() {
        return this.clipToBoundingBox;
    }

    public final void setClipToBoundingBox(BoundingBox boundingBox) {
        this.clipToBoundingBox = boundingBox;
    }

    public final List<Coordinates> getClipToPolygon() {
        return this.clipToPolygon;
    }

    public final void setClipToPolygon(List<Coordinates> coordinates) {
        this.clipToPolygon = coordinates;
    }

    public AutosuggestInputType getInputType() {
        return inputType;
    }

    public void setInputType(AutosuggestInputType inputType) {
        this.inputType = inputType;
    }

    public Boolean getPreferLand() {
        return preferLand;
    }

    public void setPreferLand(Boolean preferLand) {
        this.preferLand = preferLand;
    }
}