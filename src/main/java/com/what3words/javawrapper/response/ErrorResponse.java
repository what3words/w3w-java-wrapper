package com.what3words.javawrapper.response;

import com.google.gson.GsonBuilder;

public class ErrorResponse {
    private APIError error;

    public APIError getError() {
        return error;
    }
    
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}