package com.what3words.javawrapper.request;

import com.what3words.javawrapper.What3WordsV3;
import com.what3words.javawrapper.response.GridSectionGeoJson;

public class GridSectionGeoJsonRequest extends Request<GridSectionGeoJson> {
    private String boundingBox;

    private GridSectionGeoJsonRequest(Builder builder) {
        super(builder.api);
        boundingBox = builder.boundingBox;
    }

    private GridSectionGeoJson execute() {
        return super.execute(api.what3words().gridSectionGeoJson(boundingBox, "geojson"), GridSectionGeoJson.class);
    }

    public static class Builder extends AbstractBuilder<GridSectionGeoJson> {
        private String boundingBox;

        public Builder(What3WordsV3 api, BoundingBox boundingBox) {
            super(api);
            this.boundingBox = String.valueOf(boundingBox.sw.lat) + "," + String.valueOf(boundingBox.sw.lng) + "," +
                    String.valueOf(boundingBox.ne.lat) + "," + String.valueOf(boundingBox.ne.lng);
        }

        public GridSectionGeoJson execute() {
            return new GridSectionGeoJsonRequest(this).execute();
        }
    }
}
