package com.what3words.javawrapper.response;

import com.what3words.javawrapper.response.APIResponse.What3WordsError;

public class Response <T> {
    private transient APIResponse<T> response;

    public void setResponse(APIResponse<T> response) {
        this.response = response;
    }
    
    public What3WordsError getError() {
        return response.getError();
    }
    
    public boolean isSuccessful() {
        return response.isSuccessful();
    }
}
