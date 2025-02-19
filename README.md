# <img src="https://what3words.com/assets/images/w3w_square_red.png" width="64" height="64" alt="what3words">&nbsp;w3w-java-wrapper

[![Maven Central](https://img.shields.io/maven-central/v/com.what3words/w3w-java-wrapper)](https://central.sonatype.com/artifact/com.what3words/w3w-java-wrapper)

A Java library to use the [what3words v3 API](https://docs.what3words.com/api/v3/).

API methods are grouped into a single service object which can be centrally managed by a What3WordsV3 instance. It will act as a factory for all of the API endpoints and will automatically initialize them with your API key.

To obtain an API key, please visit [https://what3words.com/select-plan](https://what3words.com/select-plan) and sign up for an account.

## Installation

The artifact is available through <a href="https://search.maven.org/search?q=g:com.what3words">Maven Central</a>.

### Maven

```xml
<dependency>
  <groupId>com.what3words</groupId>
  <artifactId>w3w-java-wrapper</artifactId>
  <version>$latest</version>
</dependency>
```

### Gradle


implementation 'com.what3words:w3w-java-wrapper:$latest'

## Documentation

See the what3words public API [documentation](https://docs.what3words.com/api/v3/)

## General Usage

```Java
// For all requests a what3words API key is needed
What3WordsV3 api = new What3WordsV3("what3words-api-key");

// In the case that you run our Enterprise Suite API Server yourself, you may specify the URL to your own server like so:
//What3WordsV3 api = new What3WordsV3("what3words-api-key", "https://api.yourserver.com/v3/");

/**
 * Additionally, if you run the Enterprise Suite API Server there is another optional setup() parameter: customHeaders. 
 * Use this if you need to send custom headers to your own server:
 */
//Map<String, String> headers = new HashMap<String, String>();
//headers.put("Name1", "Value1");
//headers.put("Name2", "Value2");
//What3WordsV3 api = new What3WordsV3("what3words-api-key", "https://api.yourserver.com/v3/", headers);

// Create and execute a request with the 3 word address such as "filled.count.soap"
ConvertToCoordinates coordinates = api.convertToCoordinates("filled.count.soap").execute();

if (coordinates.isSuccessful()) { // the request was successful
    System.out.println("Coordinates: " + coordinates);

} else { // the request was not successful
    What3WordsError error = coordinates.getError();

    if (error == What3WordsError.BAD_WORDS) { // The three word address provided is invalid
        System.out.println("BadWords: " + error.getMessage());

    } else if (error == What3WordsError.INTERNAL_SERVER_ERROR) { // Server Error
        System.out.println("InternalServerError: " + error.getMessage());

    } else if (error == What3WordsError.NETWORK_ERROR) { // Network Error
        System.out.println("NetworkError: " + error.getMessage());

    } else { // Unknown Error
        System.out.println(error + ": " + error.getMessage());

    }
}
```

## API Methods

- [Convert To 3 Word Address](src/main/java/com/what3words/javawrapper/examples/ConvertTo3WAExample.java) - Convert a latitude and longitude to a 3 word address
- [Convert To Coordinates](src/main/java/com/what3words/javawrapper/examples/ConvertToCoordinatesExample.java) - Convert a 3 word address to a latitude and longitude
- [AutoSuggest](src/main/java/com/what3words/javawrapper/examples/AutosuggestExample.java) - AutoSuggest can take a slightly incorrect 3 word address, and suggest a list of valid 3 word addresses.
- [Grid Section](src/main/java/com/what3words/javawrapper/examples/GridSectionExample.java) - Returns a section of the 3m x 3m what3words grid for a bounding box.
- [Available Languages](src/main/java/com/what3words/javawrapper/examples/AvailableLanguagesExample.java) - Retrieves a list all available 3 word address languages.
- [Is Valid 3 Word Address](src/main/java/com/what3words/javawrapper/examples/IsValid3waExample.java) - Checks if a what3words address is valid.


## Helper Functions

### isPossible3wa
Check if a String is a possible what3words address. A reminder that this just checks the format of the text, hence why is called possible3wa, to verify if it's a real what3words address please use [Is Valid 3 Word Address](src/main/java/com/what3words/javawrapper/examples/IsValid3waExample.java).

```java
Boolean isPossible = What3WordsV3.isPossible3wa("filled.count.soap"); // returns true
Boolean isPossible = What3WordsV3.isPossible3wa("not a 3wa"); // returns false
Boolean isPossible = What3WordsV3.isPossible3wa("not.3wa address"); //returns false
```

### didYouMean3wa
Check if a String is a possible what3words address, this regex allows different separators (i.e: not using standard full stop/dot). A reminder that this just checks the format of the text, hence why is called didYouMean3wa, to verify if it's a real what3words address please use [Is Valid 3 Word Address](src/main/java/com/what3words/javawrapper/examples/IsValid3waExample.java) using full stop as a separator.

```java
Boolean isDym = What3WordsV3.didYouMean3wa("filled-count-soap"); // returns true
Boolean isDym = What3WordsV3.didYouMean3wa("not valid"); // returns false
Boolean isDym = What3WordsV3.didYouMean3wa("not.3wa address"); // returns false
Boolean isDym = What3WordsV3.didYouMean3wa("not.threewa address"); // returns true
```

### findPossible3wa
Get any possible what3words addresses from a text. Will return an empty list if no possible addresses are found. Reminder that this just checks the format of the text, hence why is called findPossible3wa, to verify if it's a real what3words address please use [Is Valid 3 Word Address](src/main/java/com/what3words/javawrapper/examples/IsValid3waExample.java) to verify each item of the list.

```java
List<String> possible = What3WordsV3.findPossible3wa("Please leave by my porch at filled.count.soap"); //returns ["filled.count.soap"]
List<String> possible = What3WordsV3.findPossible3wa("Please leave by my porch at filled.count.soap or deed.tulip.judge"); // returns ["filled.count.soap", "deed.tulip.judge"]
List<String> possible = What3WordsV3.findPossible3wa("Please leave by my porch at"); // returns []
```
