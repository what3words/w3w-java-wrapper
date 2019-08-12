package com.what3words.javawrapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class What3WordsV3Interceptor implements Interceptor {
    private String apiKey;
    private String userAgent;
    private String packageName;
    private String signature;

    public What3WordsV3Interceptor(String apiKey, String packageName, String signature) {
        this.apiKey = apiKey;
        this.userAgent = getUserAgent();
        this.packageName = packageName;
        this.signature = signature;
    }
    
    private String getUserAgent() {
        return "what3words-Java/" + getVersion() + " (Java " + System.getProperty( "java.version" ) + "; "
            + System.getProperty( "os.name" ) + " " + System.getProperty( "os.version" ) + ")";
    }

    private String getVersion() {
        Properties props = new Properties();

        try (InputStream is = getClass().getResourceAsStream("/version.properties")) {
            if (is != null) {
                props.load(is);
            }
        } catch (IOException e) {
        }

        return props.getProperty("version", "unknown-version");
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder builder = request.newBuilder();

        // set required content type, api key and request specific API version
        builder.header(What3WordsV3.HEADER_CONTENT_TYPE, What3WordsV3.CONTENT_TYPE_JSON);
        builder.header(What3WordsV3.HEADER_WHAT3WORDS_API_KEY, this.apiKey);
        builder.header(What3WordsV3.W3W_WRAPPER, this.userAgent);
        
        if (packageName != null) {
            builder.header(What3WordsV3.ANDROID_PACKAGE_HEADER, this.packageName);
        }
        if (signature != null) {
            builder.header(What3WordsV3.ANDROID_CERT_HEADER, this.signature);
        }

        return chain.proceed(builder.build());
    }
}
