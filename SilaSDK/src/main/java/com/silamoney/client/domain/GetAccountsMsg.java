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
     * @param reference
     */
    public GetAccountsMsg(String userHandle, String appHandle,String reference){
        this.header = new Header(userHandle, appHandle);
        this.header.setReference(reference);
        this.message = Message.ValueEnum.GET_ACCOUNTS_MSG.getValue();
    }
}
