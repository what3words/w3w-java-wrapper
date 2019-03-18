package com.what3words.javawrapper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class What3WordsV3Interceptor implements Interceptor {
    private String apiKey;

    public What3WordsV3Interceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder builder = request.newBuilder();

        // set required content type, api key and request specific API version
        builder.header(What3WordsV3.HEADER_CONTENT_TYPE, What3WordsV3.CONTENT_TYPE_JSON);
        builder.header(What3WordsV3.HEADER_WHAT3WORDS_API_KEY, this.apiKey);

        return chain.proceed(builder.build());
    }
}
