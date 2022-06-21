package com.what3words.javawrapper.response;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

public class GridSectionGeoJson extends Response<GridSectionGeoJson> {
    private JsonArray features = null;
    private String type = null;

    public JsonArray getFeatures() {
        return features;
    }

    public String getType() {
        return type;
    }

    public String toGeoJsonString() {
        return new GsonBuilder().create().toJson(this);
    }

    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
