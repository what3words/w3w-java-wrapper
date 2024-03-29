package com.what3words.javawrapper.request;

import com.google.gson.stream.MalformedJsonException;
import com.what3words.javawrapper.What3WordsJavaWrapper;
import com.what3words.javawrapper.response.APIError;
import com.what3words.javawrapper.response.APIResponse;
import com.what3words.javawrapper.response.AutosuggestSelection;
import com.what3words.javawrapper.response.ErrorResponse;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class AutosuggestSelectionRequest {
    protected What3WordsJavaWrapper api;
    private String rawInput;
    private String selection;
    private String rank;
    private String sourceApi;
    private String nResults;
    private String focus;
    private String nFocusResults;
    private String clipToCountry;
    private String clipToBoundingBox;
    private String clipToCircle;
    private String clipToPolygon;
    private String inputType;
    private String language;
    private String locale;
    private String preferLand;

    private AutosuggestSelectionRequest(Builder builder) {
        api = builder.api;
        rawInput = builder.rawInput;
        selection = builder.selection;
        rank = builder.rank;
        sourceApi = builder.sourceApi;
        nResults = builder.nResults;
        focus = builder.focus;
        nFocusResults = builder.nFocusResults;
        clipToCountry = builder.clipToCountry;
        clipToBoundingBox = builder.clipToBoundingBox;
        clipToCircle = builder.clipToCircle;
        clipToPolygon = builder.clipToPolygon;
        inputType = builder.inputType;
        language = builder.language;
        locale = builder.locale;
        preferLand = builder.preferLand;
    }

    private AutosuggestSelection execute() {
        if (api.getRetrofitInstance().baseUrl().host().contains(".what3words.com") || api.getRetrofitInstance().baseUrl().host().contains(".w3w.io")) {
            APIResponse<Void> response;
            try {
                response = new APIResponse<>(api.what3words().autosuggestSelection(rawInput, selection, rank, sourceApi, nResults, focus, nFocusResults, clipToCountry, clipToBoundingBox, clipToCircle, clipToPolygon, inputType, language, locale, preferLand).execute());

                if (!response.isSuccessful()) {
                    /** Attempt to parse the HTTP body of the error response */
                    Converter<ResponseBody, ErrorResponse> errorConverter =
                            api.getRetrofitInstance().responseBodyConverter(ErrorResponse.class, new Annotation[0]);

                    APIError error;
                    try {
                        error = errorConverter.convert(response.getResponse().errorBody()).getError();
                    } catch (MalformedJsonException e) {
                        error = new APIError();
                        error.setCode("UnknownError");
                        error.setMessage(response.getResponse().raw().message());
                    } catch (IOException e) {
                        error = new APIError();
                        error.setCode("NetworkError");
                        error.setMessage(e.getMessage());
                    }

                    response.setAPIError(error);
                }
            } catch (IOException e) {
                response = new APIResponse<>(null);
                APIError error = new APIError();
                error.setCode("NetworkError");
                error.setMessage(e.getMessage());
                response.setAPIError(error);
            }

            APIError error = response.getAPIError();
            if (error != null) {
                // look for the error within the available error enums
                APIResponse.What3WordsError errorEnum = APIResponse.What3WordsError.get(error.getCode());

                // Haven't found the error, return UNKNOWN_ERROR
                if (errorEnum == null) {
                    errorEnum = APIResponse.What3WordsError.UNKNOWN_ERROR;
                }

                errorEnum.setMessage(error.getMessage());

                response.setError(errorEnum);
            }

            AutosuggestSelection entity = new AutosuggestSelection();
            entity.setResponse(response);

            return entity;
        } else {
            APIResponse<Void> response = new APIResponse<>(null);
            APIError error = new APIError();
            error.setCode("NetworkError");
            error.setMessage("autosuggest-seletion not available on enterprise api.");
            response.setAPIError(error);
            AutosuggestSelection entity = new AutosuggestSelection();
            entity.setResponse(response);

            return entity;
        }
    }

    public static class Builder extends AbstractBuilder<AutosuggestSelection> {
        private String rawInput;
        private String selection;
        private String rank;
        private String sourceApi;
        private String nResults;
        private String focus;
        private String nFocusResults;
        private String clipToCountry;
        private String clipToBoundingBox;
        private String clipToCircle;
        private String clipToPolygon;
        private String inputType;
        private String language;
        private String locale;
        private String preferLand;

        public Builder(What3WordsJavaWrapper api, String rawInput, String selection, int rank, SourceApi sourceApi) {
            super(api);
            this.rawInput = rawInput;
            this.selection = selection;
            this.rank = String.valueOf(rank);
            this.sourceApi = sourceApi.toString().toLowerCase();
        }

        /**
         * Set the number of AutoSuggest results to return. A maximum of 100 results can be specified, if a number greater than this is requested,
         * this will be truncated to the maximum. The default is 3
         *
         * @param n the number of AutoSuggest results to return
         * @return a {@link Builder} instance suitable for invoking a <code>autosuggest-selection</code> API request
         */
        public Builder nResults(int n) {
            this.nResults = String.valueOf(n);
            return this;
        }

        /**
         * This is a location, specified as a latitude (often where the user making the query is). If specified, the results will be weighted to
         * give preference to those near the <code>focus</code>. For convenience, longitude is allowed to wrap around the 180 line, so 361 is equivalent to 1.
         *
         * @param coordinates the focus to use
         * @return a {@link Builder} instance suitable for invoking a <code>autosuggest-selection</code> API request
         */
        public Builder focus(Coordinates coordinates) {
            this.focus = String.valueOf(coordinates.lat) + "," + String.valueOf(coordinates.lng);
            return this;
        }

        /**
         * Specifies the number of results (must be &lt;= nResults) within the results set which will have a focus. Defaults to <code>nResults</code>.
         * This allows you to run autosuggest-with-coordinates with a mix of focussed and unfocussed results, to give you a "blend" of the two. This is exactly what the old V2
         * <code>standardblend</code> did, and <code>standardblend</code> behaviour can easily be replicated by passing <code>nFocusResults=1</code>,
         * which will return just one focussed result and the rest unfocussed.
         *
         * @param n number of results within the results set which will have a focus
         * @return a {@link Builder} instance suitable for invoking a <code>autosuggest-selection</code> API request
         */
        public Builder nFocusResults(int n) {
            this.nFocusResults = String.valueOf(n);
            return this;
        }

        /**
         * Restrict autosuggest-with-coordinates results to a circle, specified by <code>Coordinates</code> representing the centre of the circle, plus the
         * <code>radius</code> in kilometres. For convenience, longitude is allowed to wrap around 180 degrees. For example 181 is equivalent to -179.
         *
         * @param centre the centre of the circle
         * @param radius the radius of the circle in kilometres
         * @return a {@link Builder} instance suitable for invoking a <code>autosuggest-selection</code> API request
         */
        public Builder clipToCircle(Coordinates centre, double radius) {
            this.clipToCircle = String.valueOf(centre.lat) + "," + String.valueOf(centre.lng) + "," + String.valueOf(radius);
            return this;
        }

        /**
         * Restrict autosuggest-with-coordinates results to a polygon, specified by a collection of <code>Coordinates</code>. The polygon should be closed,
         * i.e. the first element should be repeated as the last element; also the list should contain at least 4 entries. The API is currently limited to
         * accepting up to 25 pairs.
         *
         * @param polygon the polygon to clip results too
         * @return a {@link Builder} instance suitable for invoking a <code>autosuggest-selection</code> API request
         */
        public Builder clipToPolygon(Coordinates... polygon) {
            List<String> coordinatesList = new ArrayList<>();
            for (Coordinates coordinates : polygon) {
                coordinatesList.add(String.valueOf(coordinates.lat));
                coordinatesList.add(String.valueOf(coordinates.lng));
            }
            this.clipToPolygon = join(",", coordinatesList.toArray(new String[0]));
            return this;
        }

        /**
         * Restrict autosuggest-with-coordinates results to a <code>BoundingBox</code>.
         *
         * @param boundingBox <code>BoundingBox</code> to clip results too
         * @return a {@link Builder} instance suitable for invoking a <code>autosuggest-selection</code> API request
         */
        public Builder clipToBoundingBox(BoundingBox boundingBox) {
            this.clipToBoundingBox = String.valueOf(boundingBox.sw.lat) + "," + String.valueOf(boundingBox.sw.lng) + "," +
                    String.valueOf(boundingBox.ne.lat) + "," + String.valueOf(boundingBox.ne.lng);
            return this;
        }

        /**
         * Restricts autosuggest-with-coordinates to only return results inside the countries specified by comma-separated list of uppercase ISO 3166-1 alpha-2 country codes
         * (for example, to restrict to Belgium and the UK, use <code>clipToCountry("GB", "BE")</code>. <code>clipToCountry</code> will also accept lowercase
         * country codes. Entries must be two a-z letters. WARNING: If the two-letter code does not correspond to a country, there is no error: API simply
         * returns no results.
         *
         * @param countryCodes countries to clip results too
         * @return a {@link Builder} instance suitable for invoking a <code>autosuggest-selection</code> API request
         */
        public Builder clipToCountry(String... countryCodes) {
            this.clipToCountry = join(",", countryCodes);
            return this;
        }

        /**
         * For normal text input, specifies a fallback language, which will help guide autosuggest-with-coordinates if the input is particularly messy. If specified,
         * this parameter must be a supported 3 word address language as an ISO 639-1 2 letter code. For voice input (see voice section),
         * language must always be specified.
         *
         * @param language the fallback language
         * @return a {@link Builder} instance suitable for invoking a <code>autosuggest-selection</code> API request
         */
        public Builder language(String language) {
            this.language = language;
            return this;
        }

        /**
         * For some of our supported languages, a what3words locale can be specified within the API request using either
         * the parameter locale or using the language parameter. The locale allows the what3words address to be displayed in a variant of a language.
         * For example, Mongolian what3words addresses can be displayed in either Cyrillic (mn_cy) or Latin (mn_la) characters and therefore by specifying the locale in the API request you can return either variant.
         * The locale will also be included in the API response to show which variant has been returned.
         *
         * @param locale the fallback locale
         * @return a {@link Builder} instance suitable for invoking a <code>autosuggest-selection</code> API request
         */
        public Builder locale(String locale) {
            this.locale = locale;
            return this;
        }

        /**
         * For power users, used to specify voice input mode. Can be <code>AutosuggestInputType.TEXT</code> (default), <code>AutosuggestInputType.VOCON_HYBRID</code>
         * or <code>AutosuggestInputType.NMDP_ASR</code>. See voice recognition section within the developer docs for more details https://docs.what3words.com/api/v3/#voice.
         *
         * @param type the AutosuggestInputType
         * @return a {@link Builder} instance suitable for invoking a <code>autosuggest-selection</code> API request
         */
        public Builder inputType(AutosuggestInputType type) {
            this.inputType = type.toString();
            return this;
        }

        public Builder preferLand(boolean preferLand) {
            this.preferLand = Boolean.toString(preferLand);
            return this;
        }

        /**
         * Set all options at once using {@link AutosuggestOptions}
         *
         * @param options the AutoSuggestOptions
         * @return a {@link AutosuggestRequest.Builder} instance suitable for invoking a <code>autosuggest</code> API request
         */
        public Builder options(AutosuggestOptions options) {
            if (options.getNResults() != null) nResults(options.getNResults());
            if (options.getFocus() != null) focus(options.getFocus());
            if (options.getNFocusResults() != null) nFocusResults(options.getNFocusResults());
            if (options.getClipToCountry() != null) clipToCountry(options.getClipToCountry().toArray(new String[]{}));
            if (options.getClipToCircle() != null) {
                if (options.getClipToCircleRadius() != null)
                    clipToCircle(options.getClipToCircle(), options.getClipToCircleRadius());
                else clipToCircle(options.getClipToCircle(), 1.0);
            }
            if (options.getClipToPolygon() != null)
                clipToPolygon(options.getClipToPolygon().toArray(new Coordinates[]{}));
            if (options.getClipToBoundingBox() != null) clipToBoundingBox(options.getClipToBoundingBox());
            if (options.getLanguage() != null) language(options.getLanguage());
            if (options.getLocale() != null) locale(options.getLocale());
            if (options.getInputType() != null) inputType(options.getInputType());
            if (options.getPreferLand() != null) preferLand(options.getPreferLand());
            return this;
        }

        /**
         * Execute the API call as represented by the values set within this {@link Builder}
         *
         * @return an {@link APIResponse} representing the response from the what3words API
         */
        public AutosuggestSelection execute() {
            return new AutosuggestSelectionRequest(this).execute();
        }

        private static String join(String separator, String... values) {
            if (values == null || values.length == 0) return "";
            StringBuilder sb = new StringBuilder();
            int end = 0;
            for (String s : values) {
                if (s != null) {
                    sb.append(s);
                    end = sb.length();
                    sb.append(separator);
                }
            }
            return sb.substring(0, end);
        }
    }
}
