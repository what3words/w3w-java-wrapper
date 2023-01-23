package com.what3words.javawrapper.request;

import com.what3words.javawrapper.What3WordsJavaWrapper;
import com.what3words.javawrapper.response.ConvertToCoordinates;

public class ConvertToCoordinatesRequest extends Request<ConvertToCoordinates> {
    private String words;
    
    private ConvertToCoordinatesRequest(Builder builder) {
        super(builder.api);
        this.words = builder.words;
    }
    
    private ConvertToCoordinates execute() {
        return super.execute(api.what3words().convertToCoordinates(words), ConvertToCoordinates.class);
    }

    public static class Builder extends AbstractBuilder<ConvertToCoordinates> {
        private String words;
        
        public Builder(What3WordsJavaWrapper api, String words) {
            super(api);
            this.words = words;
        }

        public ConvertToCoordinates execute() {
            return new ConvertToCoordinatesRequest(this).execute();
        }
    }
}
