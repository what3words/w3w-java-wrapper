package com.what3words.javawrapper.response;

public class Language {
    private String code;
    private String name;
    private String nativeName;

    public Language(String code, String name, String nativeName) {
        this.code = code;
        this.name = name;
        this.nativeName = nativeName;
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
}