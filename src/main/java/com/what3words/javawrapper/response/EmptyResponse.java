package com.what3words.javawrapper.response;

public class EmptyResponse {
    private transient APIResponse response;

    public void setResponse(APIResponse response) {
        this.response = response;
    }

    public APIResponse.What3WordsError getError() {
        return response.getError();
    }

    public boolean isSuccessful() {
        return response.isSuccessful();
    }
}
