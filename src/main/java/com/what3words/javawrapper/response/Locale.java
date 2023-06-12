package com.what3words.javawrapper.response;

public class Locale {
    private String code;
    private String name;
    private String nativeName;

    public Locale(String code, String name, String nativeName) {
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
