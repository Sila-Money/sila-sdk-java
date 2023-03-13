package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
/**
 * Object sent in the Get Statement Transactions method
 *
 * @author anuj kalal
 */
public class GetStatementTransactionsMsg {
    @SerializedName("header")
    private final Header header;

    @SerializedName("message")
    private final String message;

    @SerializedName("wallet_id")
    private final String walletId;

    @SerializedName("month")
    private final String month;

    @SerializedName("page")
    private Integer page;

    @SerializedName("per_page")
    private Integer perPage;

    /**
     * Constructor for GetStatementTransactionsMsg object.
     *
     * @param userHandle
     * @param authHandle
     * @param walletId
     * @param month
     * @param page
     * @param perPage
     */
    public GetStatementTransactionsMsg(String userHandle, String authHandle, String walletId, String month, Integer page, Integer perPage) {
        this.header = new Header(userHandle, authHandle);
        this.message = Message.ValueEnum.HEADER_MSG.getValue();
        this.walletId = walletId;
        this.month = month;
        if (page != null && page > -1)
            this.page = page;
        if (perPage != null && perPage > -1)
            this.perPage = perPage;
    }
}
