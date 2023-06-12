package com.what3words.javawrapper.request;

import java.io.Serializable;
import java.util.List;

public class AutosuggestOptions implements Serializable {
    private Coordinates focus;
    private SourceApi source;
    private String language;
    private String locale;
    private Integer nResults;
    private Integer nFocusResults;
    private List<String> clipToCountry;
    private Coordinates clipToCircle;
    private Double clipToCircleRadius;
    private BoundingBox clipToBoundingBox;
    private List<Coordinates> clipToPolygon;
    private AutosuggestInputType inputType;
    private Boolean preferLand;

    public Coordinates getFocus() {
        return this.focus;
    }

    /**
     * This is a location, specified as a latitude (often where the user making the query is). If specified, the results will be weighted to
     * give preference to those near the <code>focus</code>. For convenience, longitude is allowed to wrap around the 180 line, so 361 is equivalent to 1.
     *
     * @param coordinates the focus to use
     */
    public void setFocus(Coordinates coordinates) {
        this.focus = coordinates;
    }

    public final SourceApi getSource() {
        return this.source;
    }

    public final void setSource(SourceApi source) {
        this.source = source;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final String getLocale() { return this.locale; }

    /**
     * For normal text input, specifies a fallback language, which will help guide AutoSuggest if the input is particularly messy. If specified,
     * this parameter must be a supported 3 word address language as an ISO 639-1 2 letter code. For voice input (see voice section),
     * language must always be specified.
     *
     * @param language the fallback language
     */
    public final void setLanguage(String language) {
        this.language = language;
    }

    /**
     * For some of our supported languages, a what3words locale can be specified within the API request using either
     * the parameter locale or using the language parameter. The locale allows the what3words address to be displayed in a variant of a language.
     * For example, Mongolian what3words addresses can be displayed in either Cyrillic (mn_cy) or Latin (mn_la) characters and therefore by specifying the locale in the API request you can return either variant.
     * The locale will also be included in the API response to show which variant has been returned.
     *
     * @param locale the fallback locale
     */
    public final void setLocale(String locale) {
        this.locale = locale;
    }

    public final Integer getNResults() {
        return this.nResults;
    }

    /**
     * Set the number of AutoSuggest results to return. A maximum of 100 results can be specified, if a number greater than this is requested,
     * this will be truncated to the maximum. The default is 3
     *
     * @param n the number of AutoSuggest results to return
     */
    public final void setNResults(Integer n) {
        this.nResults = n;
    }

    public final Integer getNFocusResults() {
        return this.nFocusResults;
    }

    /**
     * Specifies the number of results (must be &lt;= nResults) within the results set which will have a focus. Defaults to <code>nResults</code>.
     * This allows you to run autosuggest with a mix of focussed and unfocussed results, to give you a "blend" of the two. This is exactly what the old V2
     * <code>standardblend</code> did, and <code>standardblend</code> behaviour can easily be replicated by passing <code>nFocusResults=1</code>,
     * which will return just one focussed result and the rest unfocussed.
     *
     * @param n number of results within the results set which will have a focus
     */
    public final void setNFocusResults(Integer n) {
        this.nFocusResults = n;
    }

    public final List<String> getClipToCountry() {
        return this.clipToCountry;
    }

    /**
     * Restricts autosuggest to only return results inside the countries specified by comma-separated list of uppercase ISO 3166-1 alpha-2 country codes
     * (for example, to restrict to Belgium and the UK, use <code>clipToCountry("GB", "BE")</code>. <code>clipToCountry</code> will also accept lowercase
     * country codes. Entries must be two a-z letters. WARNING: If the two-letter code does not correspond to a country, there is no error: API simply
     * returns no results.
     *
     * @param countries countries to clip results too
     */
    public final void setClipToCountry(List<String> countries) {
        this.clipToCountry = countries;
    }

    public final Coordinates getClipToCircle() {
        return this.clipToCircle;
    }

    /**
     * Restrict autosuggest results to a circle, specified by <code>Coordinates</code> representing the centre of the circle, plus the
     * <code>radius</code> in kilometres. For convenience, longitude is allowed to wrap around 180 degrees. For example 181 is equivalent to -179.
     *
     * @param coordinates the centre of the circle
     */
    public final void setClipToCircle(Coordinates coordinates) {
        this.clipToCircle = coordinates;
    }

    public final Double getClipToCircleRadius() {
        return this.clipToCircleRadius;
    }

    /**
     * Restrict autosuggest results to a circle, specified by <code>Coordinates</code> representing the centre of the circle, plus the
     * <code>radius</code> in kilometres. For convenience, longitude is allowed to wrap around 180 degrees. For example 181 is equivalent to -179.
     *
     * @param radius the radius of the circle in kilometres
     */
    public final void setClipToCircleRadius(Double radius) {
        this.clipToCircleRadius = radius;
    }

    public final BoundingBox getClipToBoundingBox() {
        return this.clipToBoundingBox;
    }

    /**
     * Restrict autosuggest results to a <code>BoundingBox</code>.
     *
     * @param boundingBox <code>BoundingBox</code> to clip results too
     */
    public final void setClipToBoundingBox(BoundingBox boundingBox) {
        this.clipToBoundingBox = boundingBox;
    }

    public final List<Coordinates> getClipToPolygon() {
        return this.clipToPolygon;
    }

    /**
     * Restrict autosuggest results to a polygon, specified by a collection of <code>Coordinates</code>. The polygon should be closed,
     * i.e. the first element should be repeated as the last element; also the list should contain at least 4 entries. The API is currently limited to
     * accepting up to 25 pairs.
     *
     * @param coordinates the polygon to clip results too
     */
    public final void setClipToPolygon(List<Coordinates> coordinates) {
        this.clipToPolygon = coordinates;
    }

    public AutosuggestInputType getInputType() {
        return inputType;
    }

    /**
     * For power users, used to specify voice input mode. Can be <code>AutosuggestInputType.TEXT</code> (default), <code>AutosuggestInputType.VOCON_HYBRID</code>
     * or <code>AutosuggestInputType.NMDP_ASR</code>. See voice recognition section within the developer docs for more details https://docs.what3words.com/api/v3/#voice.
     *
     * @param inputType the AutosuggestInputType
     */
    public void setInputType(AutosuggestInputType inputType) {
        this.inputType = inputType;
    }

    public Boolean getPreferLand() {
        return preferLand;
    }

    public void setPreferLand(Boolean preferLand) {
        this.preferLand = preferLand;
    }
}