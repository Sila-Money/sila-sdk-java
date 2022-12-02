package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class GetWalletStatementDataMsg {
    @SerializedName("header")
    private final Header header;
    @SerializedName("search_filters")
    private final StatementSearchFilters searchFilters;
    @SerializedName("message")
    private final String message;

    @SerializedName("wallet_id")
    private final String walletId;

    public GetWalletStatementDataMsg(String userHandle, String authHandle, String walletId, StatementSearchFilters searchFilters) {
        this.header = new Header(userHandle, authHandle);
        this.searchFilters = searchFilters;
        this.message = Message.ValueEnum.HEADER_MSG.getValue();
        this.walletId = walletId;
    }
}
