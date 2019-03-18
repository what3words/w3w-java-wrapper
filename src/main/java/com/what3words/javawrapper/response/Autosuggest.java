package com.what3words.javawrapper.response;

import java.util.List;

import com.google.gson.GsonBuilder;

public class Autosuggest extends Response<Autosuggest> {
    private List<Suggestion> suggestions = null;

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }
    
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}