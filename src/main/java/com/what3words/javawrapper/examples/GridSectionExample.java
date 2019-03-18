package com.what3words.javawrapper.examples;

import com.what3words.javawrapper.What3WordsV3;
import com.what3words.javawrapper.request.BoundingBox;
import com.what3words.javawrapper.request.Coordinates;
import com.what3words.javawrapper.response.APIResponse.Error;
import com.what3words.javawrapper.response.GridSection;

public class GridSectionExample {
    public static void main(String[] args) {
        // For all requests a what3words API key is needed
        What3WordsV3 api = new What3WordsV3("what3words-api-key");

        // Create and execute a request to obtain a grid section within the provided bounding box
        GridSection gridSection = api.gridSection(new BoundingBox(new Coordinates(51.515900, -0.212517), new Coordinates(51.527649, -0.191746))).execute();

        if (gridSection.isSuccessful()) { // the request was successful
            System.out.println("Grid: " + gridSection);

        } else {
            Error error = gridSection.getError();

            if (error == Error.BAD_BOUNDING_BOX) { // The BoundingBox is invalid
                System.out.println("BadBoundingBox: " + error.getMessage());

            } else if (error == Error.BAD_BOUNDING_BOX_TOO_BIG) { // The BoundingBox is too big
                System.out.println("BadBoundingBoxTooBig: " + error.getMessage());

            } else if (error == Error.INTERNAL_SERVER_ERROR) { // Server Error
                System.out.println("InternalServerError: " + error.getMessage());

            } else if (error == Error.NETWORK_ERROR) { // Network Error
                System.out.println("NetworkError: " + error.getMessage());

            } else {
                System.out.println(error + ": " + error.getMessage());

            }
        }
    }
}