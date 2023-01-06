package com.what3words.javawrapper.request;

import com.what3words.javawrapper.What3WordsWrapper;
import com.what3words.javawrapper.response.ConvertTo3WA;

public class ConvertTo3WARequest extends Request<ConvertTo3WA> {
    private String coordinates;
    private String language;

    private ConvertTo3WARequest(Builder builder) {
        super(builder.api);
        coordinates = builder.coordinates;
        language = builder.language;
    }
    
    private ConvertTo3WA execute() {
        return super.execute(api.what3words().convertTo3wa(coordinates, language), ConvertTo3WA.class);
    }

    public static class Builder extends AbstractBuilder<ConvertTo3WA> {
        private String coordinates;
        private String language;
        
        public Builder(What3WordsWrapper api, Coordinates coordinates) {
            super(api);
            this.coordinates = String.valueOf(coordinates.lat) + "," + String.valueOf(coordinates.lng);
        }
        
        public Builder language(String language) {
            this.language = language;
            return this;
        }
        
        public ConvertTo3WA execute() {
            return new ConvertTo3WARequest(this).execute();
        }
    }
}
