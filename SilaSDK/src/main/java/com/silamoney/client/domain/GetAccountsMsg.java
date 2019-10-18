package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object sent in the Get Accounts method.
 *
 * @author Karlo Lorenzana
 */
public class GetAccountsMsg {

    @SerializedName("header")
    private final Header header;

    @SerializedName("message")
    private final String message;

    /**
     * Constructor for GetAccountsMsg object.
     *
     * @param userHandle
     * @param appHandle
     */
    public GetAccountsMsg(String userHandle, String appHandle) {
        this.header = new Header(userHandle, appHandle);
        this.message = Message.ValueEnum.GET_ACCOUNTS_MSG.getValue();
    }
}
