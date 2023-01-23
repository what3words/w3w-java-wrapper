package com.what3words.javawrapper.response;

import java.util.List;

import com.google.gson.GsonBuilder;

public class AvailableLanguages extends Response<AvailableLanguages> {
    private List<Language> languages = null;

    public AvailableLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Language> getLanguages() {
        return languages;
    }
    
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}