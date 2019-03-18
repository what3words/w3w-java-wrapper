package com.what3words.javawrapper.response;

import com.what3words.javawrapper.response.APIResponse.Error;

public class Response <T> {
    private transient APIResponse<T> response;

    public void setResponse(APIResponse<T> response) {
        this.response = response;
    }
    
    public Error getError() {
        return response.getError();
    }
    
    public boolean isSuccessful() {
        return response.isSuccessful();
    }
}
