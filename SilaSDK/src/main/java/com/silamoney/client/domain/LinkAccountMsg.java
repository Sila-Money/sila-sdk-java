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
    private String selectedAccountId;

    @SerializedName("message")
    private final String message;

    /**
     * Constructor for LinkAccountMsg object.
     *
     * @param userHandle
     * @param accountName
     * @param publicToken
     * @param userPrivateKey
     * @param authHandle
     */
    public LinkAccountMsg(String userHandle, String accountName, String publicToken, String userPrivateKey, String authHandle) {
        this.publicToken = publicToken;
        this.accountName = accountName;
        this.header = new Header(userHandle, authHandle);
        this.message = Message.ValueEnum.LINK_ACCOUNT_MSG.getValue();
    }
}
