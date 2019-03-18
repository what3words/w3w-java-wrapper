package com.what3words.javawrapper.response;

import java.util.List;

import com.google.gson.GsonBuilder;

public class GridSection extends Response<GridSection> {
    private List<Line> lines = null;

    public List<Line> getLines() {
        return lines;
    }
    
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}