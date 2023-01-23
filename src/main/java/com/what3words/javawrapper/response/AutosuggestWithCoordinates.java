package com.what3words.javawrapper.response;

import java.util.List;

import com.google.gson.GsonBuilder;

public class AutosuggestWithCoordinates extends Response<AutosuggestWithCoordinates>{
	
	private List<SuggestionWithCoordinates> suggestions = null;

	public AutosuggestWithCoordinates() {

	}

	public AutosuggestWithCoordinates(List<SuggestionWithCoordinates> suggestions) {
		this.suggestions = suggestions;
	}

	public List<SuggestionWithCoordinates> getSuggestions() {
		return suggestions;
	}

	@Deprecated
	public void setSuggestions(List<SuggestionWithCoordinates> suggestions) {
		this.suggestions = suggestions;
	}

	public String toString() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}

}
