package com.what3words.javawrapper.response;

import java.util.List;

public class Language {
    private String code;
    private String name;
    private String nativeName;
    private List<Locale> locales;

    public Language(String code, String name, String nativeName) {
        this.code = code;
        this.name = name;
        this.nativeName = nativeName;
    }

    public Language(String code, String name, String nativeName, List<Locale> locales) {
        this.code = code;
        this.name = name;
        this.nativeName = nativeName;
        this.locales = locales;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public List<Locale> getLocales() {
        return locales;
    }
}

