package com.what3words.javawrapper.request;

import com.what3words.javawrapper.What3WordsJavaWrapper;
import com.what3words.javawrapper.response.GridSection;

public class GridSectionRequest extends Request<GridSection> {
    private String boundingBox;

    private GridSectionRequest(Builder builder) {
        super(builder.api);
        boundingBox = builder.boundingBox;
    }

    private GridSection execute() {
        return super.execute(api.what3words().gridSection(boundingBox, "json"), GridSection.class);
    }

    public static class Builder extends AbstractBuilder<GridSection> {
        private String boundingBox;
        
        public Builder(What3WordsJavaWrapper api, BoundingBox boundingBox) {
            super(api);
            this.boundingBox = String.valueOf(boundingBox.sw.lat) + "," + String.valueOf(boundingBox.sw.lng) + "," +
                    String.valueOf(boundingBox.ne.lat) + "," + String.valueOf(boundingBox.ne.lng);
        }

        public GridSection execute() {
            return new GridSectionRequest(this).execute();
        }
    }
}

