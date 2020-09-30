package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object sent in redeem sila method.
 *
 * @author Karlo Lorenzana
 */
public class RedeemMsg {

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
     * Constructor for RedeemMsg object.
     *
     * @param appHandle
     * @param message
     */
    public RedeemMsg(String appHandle, AccountTransactionMessage message) {
        this.header = new Header(message.getUserHandle(), appHandle);
        this.amount = message.getAmount();
        this.accountName = message.getAccountName();
        this.message = Message.ValueEnum.REDEEM_MSG.getValue();
        this.descriptor = message.getDescriptor();
        this.businessUuid = message.getBusinessUuid();
    }
}
