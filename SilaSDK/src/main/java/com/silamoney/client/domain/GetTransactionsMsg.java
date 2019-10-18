package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object sent in the GetTransaction method.
 *
 * @author loren
 */
public class GetTransactionsMsg {

    @SerializedName("header")
    private final Header header;

    @SerializedName("search_filters")
    private final SearchFilters searchFilters;

    @SerializedName("message")
    private final String message;

    /**
     * Constructor for GetTransactionMsg object.
     *
     * @param userHandle
     * @param appHandle
     * @param searchFilters
     */
    public GetTransactionsMsg(String userHandle, String appHandle,
            SearchFilters searchFilters) {
        this.searchFilters = searchFilters;
        this.message = Message.ValueEnum.GET_TRANSACTIONS_MSG.getValue();
        this.header = new Header(userHandle, appHandle);
    }

}
