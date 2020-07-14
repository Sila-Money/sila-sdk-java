package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object sent in the link account method.
 *
 * @author Karlo Lorenzana
 */
public class LinkAccountMsg {

    @SerializedName("public_token")
    private final String publicToken;

    @SerializedName("account_name")
    private final String accountName;

    @SerializedName("header")
    private final Header header;

    @SerializedName("selected_account_id")
    private final String selectedAccountId;

    @SerializedName("message")
    private final String message;

    @SerializedName("account_number")
    private final String accountNumber;

    @SerializedName("routing_number")
    private final String routingNumber;

    @SerializedName("account_type")
    private final String accountType;

    /**
     * Constructor for LinkAccountMsg object.
     *
     * @param userHandle
     * @param accountName
     * @param publicToken
     * @param accountId
     * @param accountNumber
     * @param routingNumber
     * @param accountType
     * @param appHandle
     */
    public LinkAccountMsg(String userHandle, String accountName, String publicToken, String accountId,
            String accountNumber, String routingNumber, String accountType, String appHandle) {
        this.publicToken = publicToken;
        this.accountName = accountName;
        this.selectedAccountId = accountId;
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
        this.accountType = accountType;
        this.header = new Header(userHandle, appHandle);
        this.message = Message.ValueEnum.LINK_ACCOUNT_MSG.getValue();
    }
}
