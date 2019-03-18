# <img src="https://what3words.com/assets/images/w3w_square_red.png" width="64" height="64" alt="what3words">&nbsp;w3w-java-wrapper

A Java library to use the [what3words v3 API](https://docs.what3words.com/api/v3/).

API methods are grouped into a single service object which can be centrally managed by a What3WordsV3 instance. It will act as a factory for all of the API endpoints and will automatically initialize them with your API key.

To obtain an API key, please visit [https://accounts.what3words.com/](https://accounts.what3words.com/) and sign up for an account.

## Installation

The artifact is available through <a href="https://search.maven.org/search?q=g:com.what3words">Maven Central</a>.

### Maven

```xml
<dependency>
  <groupId>com.what3words</groupId>
  <artifactId>w3w-java-wrapper</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### Gradle

```groovy
implementation 'com.what3words:w3w-java-wrapper:0.0.1-SNAPSHOT'
```

## Documentation

See the what3words public API [documentation](https://docs.what3words.com/api/v3/)

## General Usage

```Java
// For all requests a what3words API key is needed
What3WordsV3 api = new What3WordsV3("what3words-api-key");

// Create and execute a request with the 3 word address such as "filled.count.soap"
ConvertToCoordinates coordinates = api.convertToCoordinates("filled.count.soap").execute();

if (coordinates.isSuccessful()) { // the request was successful
    System.out.println("Coordinates: " + coordinates);

} else { // the request was not successful
    Error error = coordinates.getError();

    if (error == Error.BAD_WORDS) { // The three word address provided is invalid
        System.out.println("BadWords: " + error.getMessage());

    } else if (error == Error.INTERNAL_SERVER_ERROR) { // Server Error
        System.out.println("InternalServerError: " + error.getMessage());

    } else if (error == Error.NETWORK_ERROR) { // Network Error
        System.out.println("NetworkError: " + error.getMessage());

    } else { // Unknown Error
        System.out.println(error + ": " + error.getMessage());

    }
}
```

## Android

### Manifest Permission

The `android.permission.INTERNET` permission is needed in order to perform network operations in your application. Add the following permission to the `AndroidManifest.xml` file.

`<uses-permission android:name="android.permission.INTERNET" />`

### Main Thread Loop

Because it is not possible to perform a networking operation on the main application thread, API calls need to be made in a background thread. It is recomended to use 
[IntentService](https://developer.android.com/reference/android/app/IntentService) functionality to perform asynchronous requests in a production application, however,
for simple demonstration purposes API requests can be made in a simple child thread.

```Java
new Thread(new Runnable() {
    public void run() {
        // For all requests a what3words API key is needed
        What3WordsV3 api = new What3WordsV3("what3words-api-key");

        // Create and execute a request with the 3 word address such as "filled.count.soap"
        ConvertToCoordinates coordinates = api.convertToCoordinates("filled.count.soap").execute();

        if (coordinates.isSuccessful()) { // the request was successful
            System.out.println("Coordinates: " + coordinates);

        } else { // the request was not successful
            Error error = coordinates.getError();

            if (error == Error.BAD_WORDS) { // The three word address provided is invalid
                System.out.println("BadWords: " + error.getMessage());

            } else if (error == Error.INTERNAL_SERVER_ERROR) { // Server Error
                System.out.println("InternalServerError: " + error.getMessage());

            } else if (error == Error.NETWORK_ERROR) { // Network Error
                System.out.println("NetworkError: " + error.getMessage());

            } else { // Unknown Error
                System.out.println(error + ": " + error.getMessage());

            }
        }
    }
}).start();
```

## API Methods

- [Convert To 3 Word Address](src/main/java/com/what3words/javawrapper/examples/ConvertTo3WAExample.java) - Convert a latitude and longitude to a 3 word address
- [Convert To Coordinates](src/main/java/com/what3words/javawrapper/examples/ConvertToCoordinatesExample.java) - Convert a 3 word address to a latitude and longitude
- [AutoSuggest](src/main/java/com/what3words/javawrapper/examples/AutosuggestExample.java) - AutoSuggest can take a slightly incorrect 3 word address, and suggest a list of valid 3 word addresses.
- [Grid Section](src/main/java/com/what3words/javawrapper/examples/GridSectionExample.java) - Returns a section of the 3m x 3m what3words grid for a bounding box.
- [Available Languages](src/main/java/com/what3words/javawrapper/examples/AvailableLanguagesExample.java) - Retrieves a list all available 3 word address languagesw3w-java-wrapper