package com.what3words.javawrapper.response;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import retrofit2.Response;

public class APIResponse<T> {
    private Response<T> response;
    @SerializedName("error")
    private APIError apiError;
    private Error error;
    
    public APIResponse (Response<T> response) {
        this.response = response;
    }

    public Response<T> getResponse() {
        return response;
    }
    
    public boolean isSuccessful() {
        return response != null && response.isSuccessful();
    }
    
    public T body() {
        return response == null ? null : response.body();
    }

    public Error getError() {
        return error;
    }

    public void setError(Error errorEnum) {
        this.error = errorEnum;
    }
    
    public APIError getAPIError() {
        return apiError;
    }

    public void setAPIError(APIError error) {
        this.apiError = error;
    }
    
    public enum Error {
        BAD_COORDINATES("BadCoordinates"),
        BAD_LANGUAGE("BadLanguage"),
        
        BAD_WORDS("BadWords"),

        BAD_INPUT("BadInput"),
        BAD_N_RESULTS("BadNResults"),
        BAD_N_FOCUS_RESULTS("BadNFocusResults"),
        BAD_FOCUS("BadFocus"),
        BAD_CLIP_TO_CIRCLE("BadClipToCircle"),
        BAD_CLIP_TO_BOUNDING_BOX("BadClipToBoundingBox"),
        BAD_CLIP_TO_COUNTRY("BadClipToCountry"),
        BAD_CLIP_TO_POLYGON("BadClipToPolygon"),
        BAD_INPUT_TYPE("BadInputType"),

        BAD_BOUNDING_BOX("BadBoundingBox"),
        BAD_BOUNDING_BOX_TOO_BIG("BadBoundingBoxTooBig"),
        
        INTERNAL_SERVER_ERROR("InternalServerError"),
        INVALID_KEY("InvalidKey"),
        UNKNOWN_ERROR("UnknownError"),
        NETWORK_ERROR("NetworkError");

        private final String key;
        private String message;

        private static final Map<String, Error> lookup = new HashMap<String, Error>();

        static {
            for (Error d : Error.values()) {
                lookup.put(d.getKey(), d);
            }
        }

        private Error(String key) {
            this.key = key;
        }

        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }

        public String getKey() {
            return key;
        }

        public static Error get(String key) {
            return lookup.get(key);
        }
    }
}
