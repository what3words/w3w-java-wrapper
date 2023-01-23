package com.what3words.javawrapper.request;

import com.what3words.javawrapper.What3WordsJavaWrapper;
import com.what3words.javawrapper.request.ConvertTo3WARequest.Builder;
import com.what3words.javawrapper.response.APIResponse;

/**
 * <code>Builder</code> class representing the API call to be made. Use any builder methods available to set request parameters
 * before calling <code>execute()</code> to invoke the API request
 */
public abstract class AbstractBuilder<T> {
    protected What3WordsJavaWrapper api;
    
    protected AbstractBuilder(What3WordsJavaWrapper api) {
        this.api = api;
    }
    
    /**
     * Execute the API call as represented by the values set within this {@link Builder}
     * 
     * @return an {@link APIResponse} representing the response from the what3words API
     */
    public abstract T execute();
}
