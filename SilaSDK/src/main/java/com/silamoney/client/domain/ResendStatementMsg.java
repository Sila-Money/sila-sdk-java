package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object sent in the Get Statement Transactions method
 *
 * @author anuj kalal
 */
public class ResendStatementMsg {
    @SerializedName("header")
    private final Header header;

    @SerializedName("statement_id")
    private final String statementId;

    @SerializedName("email")
    private final String email;


    /**
     * Constructor for GetStatementTransactionsMsg object.
     *
     * @param userHandle
     * @param authHandle
     * @param statementId
     * @param email
     */
    public ResendStatementMsg(String userHandle, String authHandle, String statementId, String email) {
        this.header = new Header(userHandle, authHandle);
        this.statementId = statementId;
        this.email = email;
    }
}
