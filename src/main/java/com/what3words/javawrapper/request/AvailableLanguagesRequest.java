package com.what3words.javawrapper.request;

import com.what3words.javawrapper.What3WordsJavaWrapper;
import com.what3words.javawrapper.response.APIResponse;
import com.what3words.javawrapper.response.AvailableLanguages;

public class AvailableLanguagesRequest extends Request<AvailableLanguages> {
    private AvailableLanguagesRequest(Builder builder) {
        super(builder.api);
    }

    private AvailableLanguages execute() {
        return super.execute(api.what3words().availableLanguages(), AvailableLanguages.class);
    }

    public static class Builder extends AbstractBuilder<AvailableLanguages> {
        public Builder(What3WordsJavaWrapper api) {
            super(api);
        }

        /**
         * Execute the API call as represented by the values set within this {@link Builder}
         * 
         * @return an {@link APIResponse} representing the response from the what3words API
         */
        public AvailableLanguages execute() {
            return new AvailableLanguagesRequest(this).execute();
        }
    }
}
