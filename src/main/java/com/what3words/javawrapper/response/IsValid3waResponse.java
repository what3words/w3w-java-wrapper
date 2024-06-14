package com.what3words.javawrapper.response;

public class IsValid3waResponse {
    private Boolean isValid = null;
    private APIResponse.What3WordsError error = null;

    public static IsValid3waResponse error(APIResponse.What3WordsError error) {
        IsValid3waResponse res = new IsValid3waResponse();
        res.error = error;
        return res;
    }

    public static IsValid3waResponse success(Boolean isValid) {
        IsValid3waResponse res = new IsValid3waResponse();
        res.isValid = isValid;
        return res;
    }

    public APIResponse.What3WordsError getError() {
        return error;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public boolean isSuccessful() {
        return isValid != null && error == null;
    }
}
