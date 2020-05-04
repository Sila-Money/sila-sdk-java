package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object used in the Issue Sila method.
 *
 * @author Karlo Lorenzana
 */
public class IssueMsg {

    @SerializedName("amount")
    private final int amount;

    @SerializedName("account_name")
    private final String accountName;

    @SerializedName("header")
    private final Header header;

    @SerializedName("message")
    private final String message;
    
    @SerializedName("descriptor")
    private final String descriptor;
    
    @SerializedName("business_uuid")
    private final String businessUuid;

    /**
     * Constructor for IssueMsg object.
     *
     * @param userHandle
     * @param accountName
     * @param amount
     * @param descriptor
     * @param businessUuid 
     * @param appHandle
     */
    public IssueMsg(String userHandle, String accountName, int amount, String descriptor, String businessUuid, String appHandle) {
        this.accountName = accountName;
        this.amount = amount;
        this.header = new Header(userHandle, appHandle);
        this.message = Message.ValueEnum.ISSUE_MSG.getValue();
        this.descriptor = descriptor;
        this.businessUuid = businessUuid;
    }
}
