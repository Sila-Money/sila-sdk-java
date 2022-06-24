package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class GetWebhooksMsg {
    @SerializedName("header")
    private final Header header;

    @SerializedName("search_filters")
    private final WebhookSearchFilters searchFilters;

    public GetWebhooksMsg(String userHandle, String authHandle, WebhookSearchFilters searchFilters) {
        this.header = new Header(userHandle, authHandle);
        this.searchFilters = searchFilters;
    }
}
