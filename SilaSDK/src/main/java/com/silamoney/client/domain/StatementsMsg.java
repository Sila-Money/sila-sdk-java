package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class StatementsMsg {
    @SerializedName("header")
    private final Header header;
    @SerializedName("search_filters")
    private final StatementsSearchFilters searchFilters;

    public StatementsMsg(String userHandle, String authHandle, StatementsSearchFilters searchFilters) {
        this.header = new Header(userHandle, authHandle);
        this.searchFilters = searchFilters;

    }
}
