package com.what3words.javawrapper.response;

public class APIError {
    private String code;
    private String message;
    private APIError errorEnum;

    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorEnum(APIError errorEnum) {
        this.errorEnum = errorEnum;
    }
    
    public APIError getErrorEnum() {
        return errorEnum;
    }
}