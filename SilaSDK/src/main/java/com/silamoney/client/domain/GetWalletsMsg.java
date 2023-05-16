package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object sent in the Get Wallets method
 *
 * @author wzelada
 */
public class GetWalletsMsg {

    @SerializedName("header")
    private final Header header;

    @SerializedName("search_filters")
    private final SearchFilters searchFilters;

    public GetWalletsMsg(String userHandle, SearchFilters searchFilters,
                         String appHandle,String reference) {
        this.searchFilters = searchFilters;
        this.header = new Header(userHandle, appHandle);
        this.header.setReference(reference);
    }
}
