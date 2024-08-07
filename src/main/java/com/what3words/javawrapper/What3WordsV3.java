package com.what3words.javawrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.what3words.javawrapper.request.*;
import com.what3words.javawrapper.response.Autosuggest;
import com.what3words.javawrapper.response.IsValid3waResponse;
import com.what3words.javawrapper.response.Suggestion;
import com.what3words.javawrapper.services.What3WordsV3Service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class What3WordsV3 implements What3WordsJavaWrapper {
    private static String DEFAULT_ENDPOINT = "https://api.what3words.com/v3/";

    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String HEADER_WHAT3WORDS_API_KEY = "X-Api-Key";
    public static final String W3W_WRAPPER = "X-W3W-Wrapper";
    public static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    public static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";

    /**
     * Check if {@link String} is a possible what3words address. A reminder that this just checks the format of the text, hence why is called possible3wa, to verify if it's a real what3words address please use {@link ConvertTo3WARequest} or {@link AutosuggestRequest}.
     *
     * @param text the {@link String} to match using our regex.
     * @return a {@link Boolean} which will be true if it's a match against our regex, and false if isn't.
     */
    public static Boolean isPossible3wa(String text) {
        String regex =
                "^/*(?:(?:\\p{L}\\p{M}*)+[.｡。･・︒។։။۔።।](?:\\p{L}\\p{M}*)+[.｡。･・︒។։။۔።।](?:\\p{L}\\p{M}*)+|(?:\\p{L}\\p{M}*)+([\u0020\u00A0](?:\\p{L}\\p{M}*)+){1,3}[.｡。･・︒។։။۔።।](?:\\p{L}\\p{M}*)+([\u0020\u00A0](?:\\p{L}\\p{M}*)+){1,3}[.｡。･・︒។։။۔።।](?:\\p{L}\\p{M}*)+([\u0020\u00A0](?:\\p{L}\\p{M}*)+){1,3})$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(text).find();
    }

    /**
     * Check if {@link String} is a possible what3words address, this regex allows different separators (i.e: not using standard full stop/dot). A reminder that this just checks the format of the text, hence why is called didYouMean3wa, to verify if it's a real what3words address please use {@link ConvertTo3WARequest} or {@link AutosuggestRequest} using full stop as a separator.
     *
     * @param text the {@link String} to match using our did you mean regex.
     * @return a {@link Boolean} which will be true if it's a match against our regex, and false if isn't.
     */
    public static Boolean didYouMean3wa(String text) {
        String dymRegex =
                "^/*(?:\\p{L}\\p{M}*){1,}[.｡。･・︒។։။۔።। ,\\\\^_/+'&\\:;|　-]{1,2}(?:\\p{L}\\p{M}*){1,}[.｡。･・︒។։။۔።। ,\\\\^_/+'&\\:;|　-]{1,2}(?:\\p{L}\\p{M}*){1,}$";
        Pattern pattern = Pattern.compile(dymRegex);
        return pattern.matcher(text).find();
    }

    /**
     * Get any possible what3words addresses from {@link String} text. Will return an empty list if no possible addresses are found.
     * Reminder that this just checks the format of the text, hence why is called findPossible3wa, to verify if it's a real what3words address please use {@link ConvertTo3WARequest} or {@link AutosuggestRequest} to verify the results of this search.
     *
     * @param text the {@link String} to find possible what3words addresses using our regex.
     * @return a {@link Boolean} which will be true if it's a match against our regex, false if isn't.
     */
    public static List<String> findPossible3wa(String text) {
        List<String> allMatches = new ArrayList<>();
        String searchRegex =
                "(?:\\p{L}\\p{M}*){1,}[.｡。･・︒។։။۔።।](?:\\p{L}\\p{M}*){1,}[.｡。･・︒។։။۔።।](?:\\p{L}\\p{M}*){1,}";
        Matcher pattern = Pattern.compile(searchRegex).matcher(text);
        while (pattern.find()) {
            allMatches.add(pattern.group());
        }
        return allMatches;
    }

    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    /**
     * Get a new API manager instance.
     *
     * @param apiKey Your what3words API key obtained from https://accounts.what3words.com
     */
    public What3WordsV3(String apiKey) {
        this(apiKey, DEFAULT_ENDPOINT);
    }

    /**
     * Get a new API manager instance.
     *
     * @param apiKey   Your what3words API key obtained from https://accounts.what3words.com
     * @param endpoint override the default public API endpoint
     */
    public What3WordsV3(String apiKey, String endpoint) {
        this(apiKey, endpoint, null, null, null);
    }

    /**
     * Get a new API manager instance.
     *
     * @param apiKey   Your what3words API key obtained from https://accounts.what3words.com
     * @param endpoint override the default public API endpoint
     * @param headers  add any custom HTTP headers to send in each request
     */
    public What3WordsV3(String apiKey, String endpoint, Map<String, String> headers) {
        this(apiKey, endpoint, null, null, headers);
    }

    /**
     * Get a new API manager instance.
     *
     * @param apiKey      Your what3words API key obtained from https://accounts.what3words.com
     * @param packageName For use within Android applications to provide the application package name as part of API key restriction
     * @param signature   For use within Android applications to provide the application SHA1 signature as part of API key restriction
     * @param headers     add any custom HTTP headers to send in each request
     */
    protected What3WordsV3(String apiKey, String packageName, String signature, Map<String, String> headers) {
        setupHttpClient(apiKey, DEFAULT_ENDPOINT, packageName, signature, headers);
    }

    /**
     * Get a new API manager instance.
     *
     * @param apiKey      Your what3words API key obtained from https://accounts.what3words.com
     * @param endpoint    override the default public API endpoint
     * @param packageName For use within Android applications to provide the application package name as part of API key restriction
     * @param signature   For use within Android applications to provide the application SHA1 signature as part of API key restriction
     * @param headers     add any custom HTTP headers to send in each request
     */
    protected What3WordsV3(String apiKey, String endpoint, String packageName, String signature, Map<String, String> headers) {
        setupHttpClient(apiKey, endpoint, packageName, signature, headers);
    }

    private void setupHttpClient(String apiKey, String endpoint, String packageName, String signature, Map<String, String> headers) {
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new What3WordsV3Interceptor(apiKey, packageName, signature, headers))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClientInstance()).build();
    }

    /**
     * Return the {@link Retrofit} instance. If called for the first time builds the instance.
     *
     * @return this retrofit instance
     */
    public Retrofit getRetrofitInstance() {
        return retrofit;
    }

    private OkHttpClient getOkHttpClientInstance() {
        return okHttpClient;
    }

    /**
     * Convert a latitude and longitude to a 3 word address, in the language of your choice. It also returns country,
     * the bounds of the grid square, a nearest place (such as a local town) and a link to our map site.
     *
     * @param coordinates the latitude and longitude of the location to convert to 3 word address
     * @return a {@link ConvertTo3WARequest.Builder} instance suitable for invoking a <code>convert-to-3wa</code> API request
     */
    public ConvertTo3WARequest.Builder convertTo3wa(Coordinates coordinates) {
        return new ConvertTo3WARequest.Builder(this, coordinates);
    }

    /**
     * Convert a 3 word address to a latitude and longitude. It also returns country, the bounds of the grid square,
     * a nearest place (such as a local town) and a link to our map site.
     *
     * @param words A 3 word address as a string. It must be three words separated with dots or a Japanese middle dot character (・).
     *              Words separated by spaces will be rejected. Optionally, the 3 word address can be prefixed with ///
     * @return a {@link ConvertToCoordinatesRequest.Builder} instance suitable for invoking a <code>convert-to-coordinates</code> API request
     */
    public ConvertToCoordinatesRequest.Builder convertToCoordinates(String words) {
        return new ConvertToCoordinatesRequest.Builder(this, words);
    }

    /**
     * AutoSuggest can take a slightly incorrect 3 word address, and suggest a list of valid 3 word addresses. It has powerful
     * features which can, for example, optionally limit results to a country or area, and prefer results which are near the user.
     * For full details, please see the complete API documentation at https://docs.what3words.com/api/v3/#autosuggest
     *
     * @param input The full or partial 3 word address to obtain suggestions for. At minimum this must be the first two complete
     *              words plus at least one character from the third word.
     * @return a {@link AutosuggestRequest.Builder} instance suitable for invoking a <code>autosuggest</code> API request
     */
    public AutosuggestRequest.Builder autosuggest(String input) {
        return new AutosuggestRequest.Builder(this, input);
    }

    /**
     * Autosuggest with coordinates can take a slightly incorrect 3 word address, and suggest a list of valid 3 word addresses. It has powerful
     * features which can, for example, optionally limit results to a country or area, and prefer results which are near the user.
     * In addition to all the functionality provided by Autosuggest, Autosuggest with coordinates also returns coordinates within each suggestion.
     * For full details, please see the complete API documentation at https://docs.what3words.com/api/v3/#autosuggest
     *
     * @param input The full or partial 3 word address to obtain suggestions for. At minimum this must be the first two complete
     *              words plus at least one character from the third word.
     * @return a {@link AutosuggestRequest.Builder} instance suitable for invoking a <code>autosuggest-with-coordinates</code> API request
     */
    public AutosuggestWithCoordinatesRequest.Builder autosuggestWithCoordinates(String input) {
        return new AutosuggestWithCoordinatesRequest.Builder(this, input);
    }

    /**
     * AutosuggestSelection enables simple reporting for what3words address selections in accounts.what3words.com.
     * It should be called once in conjunction with autosuggest or autosuggest-with-coordinates.
     * when an end user picks a what3words address from the suggestions presented to them.
     *
     * @param rawInput  The full or partial 3 word address used on the autosuggest or autosuggest-with-coordinates call.
     * @param sourceApi The source of the selected autosuggest, can be 'SourceApi.TEXT' or 'SourceApi.VOICE'.
     * @param selection The 3 word address of the selected suggestion.
     * @param rank      The rank of the selected suggestion.
     * @return a {@link AutosuggestSelectionRequest.Builder} instance suitable for invoking a <code>autosuggest-selection</code> API request
     */
    public AutosuggestSelectionRequest.Builder autosuggestionSelection(String rawInput, String selection, int rank, SourceApi sourceApi) {
        return new AutosuggestSelectionRequest.Builder(this, rawInput, selection, rank, sourceApi);
    }

    /**
     * Returns a section of the 3m x 3m what3words grid for a bounding box. The bounding box is specified by lat,lng,lat,lng
     * as south,west,north,east.
     *
     * @param boundingBox <code>BoundingBox</code>, for which the grid should be returned. The requested box must not exceed 4km
     *                    from corner to corner. Latitudes must be &gt;= -90 and &lt;= 90, but longitudes are allowed to wrap around 180. To specify a
     *                    bounding-box that crosses the anti-meridian, use longitude greater than 180.
     * @return a {@link GridSectionRequest.Builder} instance suitable for invoking a <code>grid-section</code> API request
     */
    public GridSectionRequest.Builder gridSection(BoundingBox boundingBox) {
        return new GridSectionRequest.Builder(this, boundingBox);
    }

    /**
     * Returns a section of the 3m x 3m what3words grid for a bounding box with GeoJson format. The bounding box is specified by lat,lng,lat,lng
     * as south,west,north,east.
     *
     * @param boundingBox <code>BoundingBox</code>, for which the grid should be returned. The requested box must not exceed 4km
     *                    from corner to corner. Latitudes must be &gt;= -90 and &lt;= 90, but longitudes are allowed to wrap around 180. To specify a
     *                    bounding-box that crosses the anti-meridian, use longitude greater than 180.
     * @return a {@link GridSectionRequest.Builder} instance suitable for invoking a <code>grid-section</code> API request
     */
    public GridSectionGeoJsonRequest.Builder gridSectionGeoJson(BoundingBox boundingBox) {
        return new GridSectionGeoJsonRequest.Builder(this, boundingBox);
    }

    /**
     * Retrieves a list all available 3 word address languages, including the ISO 639-1 2 letter code, english name and native name.
     *
     * @return a {@link AvailableLanguagesRequest.Builder} instance suitable for invoking a <code>available-languages</code> API request
     */
    public AvailableLanguagesRequest.Builder availableLanguages() {
        return new AvailableLanguagesRequest.Builder(this);
    }

    public What3WordsV3Service what3words() {
        return getRetrofitInstance().create(What3WordsV3Service.class);
    }

    /**
     * Checks if a given what3words address is valid.
     * @param words The what3words address to validate.
     * @return {@link Boolean} value indicating whether the what3words address is valid.
     */
    public IsValid3waResponse isValid3wa(String words) {
        if (!isPossible3wa(words)) {
            return IsValid3waResponse.success(false);
        }
        Autosuggest autosuggest = autosuggest(words).execute();
        if (!autosuggest.isSuccessful()) {
            return IsValid3waResponse.error(autosuggest.getError());
        }
        for (Suggestion suggestion : autosuggest.getSuggestions()) {
            if (suggestion.getWords().replace("/", "")
                    .equalsIgnoreCase(words.replace("/", ""))) {
                return IsValid3waResponse.success(true);
            }
        }
        return IsValid3waResponse.success(false);
    }
}
