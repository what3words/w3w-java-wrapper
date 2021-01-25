package com.what3words.javawrapper.response;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import retrofit2.Response;

public class APIResponse<T> {
    private Response<T> response;
    @SerializedName("error")
    private APIError apiError;
    private What3WordsError error;
    
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

    public What3WordsError getError() {
        return error;
    }

    public void setError(What3WordsError errorEnum) {
        this.error = errorEnum;
    }
    
    public APIError getAPIError() {
        return apiError;
    }

    public void setAPIError(APIError error) {
        this.apiError = error;
    }
    
    public enum What3WordsError {
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
        SUSPENDED_KEY("SuspendedKey"),
        UNKNOWN_ERROR("UnknownError"),
        NETWORK_ERROR("NetworkError"),

        INVALID_API_VERSION("InvalidApiVersion"),
        INVALID_REFERRER("InvalidReferrer"),
        INVALID_IP_ADDRESS("InvalidIpAddress"),
        INVALID_APP_CREDENTIALS("InvalidAppCredentials");

        private final String key;
        private String message;

        private static final Map<String, What3WordsError> lookup = new HashMap<String, What3WordsError>();

        static {
            for (What3WordsError d : What3WordsError.values()) {
                lookup.put(d.getKey(), d);
            }
        }

        private What3WordsError(String key) {
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

        public static What3WordsError get(String key) {
            return lookup.get(key);
        }
    }
}
