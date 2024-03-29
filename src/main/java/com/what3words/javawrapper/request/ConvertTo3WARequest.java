package com.what3words.javawrapper.request;

import com.what3words.javawrapper.What3WordsJavaWrapper;
import com.what3words.javawrapper.response.ConvertTo3WA;

public class ConvertTo3WARequest extends Request<ConvertTo3WA> {
    private String coordinates;
    private String language;
    private String locale;

    private ConvertTo3WARequest(Builder builder) {
        super(builder.api);
        coordinates = builder.coordinates;
        language = builder.language;
        locale = builder.locale;
    }
    
    private ConvertTo3WA execute() {
        return super.execute(api.what3words().convertTo3wa(coordinates, language, locale), ConvertTo3WA.class);
    }

    public static class Builder extends AbstractBuilder<ConvertTo3WA> {
        private String coordinates;
        private String language;
        private String locale;

        public Builder(What3WordsJavaWrapper api, Coordinates coordinates) {
            super(api);
            this.coordinates = String.valueOf(coordinates.lat) + "," + String.valueOf(coordinates.lng);
        }
        
        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder locale(String locale) {
            this.locale = locale;
            return this;
        }

        public String getLanguage() {
            return language;
        }


        public String getLocale() {
            return locale;
        }

        public ConvertTo3WA execute() {
            return new ConvertTo3WARequest(this).execute();
        }
    }
}
