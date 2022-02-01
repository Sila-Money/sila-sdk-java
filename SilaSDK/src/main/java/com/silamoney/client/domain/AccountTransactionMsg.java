package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object used in the Issue Sila method.
 *
 * @author Karlo Lorenzana
 */
public class AccountTransactionMsg {
    @SerializedName("amount")
    private final int amount;
    @SerializedName("account_name")
    private String accountName;
    @SerializedName("header")
    private final Header header;
    @SerializedName("message")
    private final String message;
    @SerializedName("descriptor")
    private final String descriptor;
    @SerializedName("business_uuid")
    private final String businessUuid;
    @SerializedName("processing_type")
    private final String processingType;
    @SerializedName("card_name")
    private String cardName;
    @SerializedName("source_id")
    private String sourceId;
    @SerializedName("destination_id")
    private String destinationId;

    /**
     * Constructor for IssueMsg object.
     *
     * @param message
     */
    public AccountTransactionMsg(String authHandle, AccountTransactionMessage message, Message.ValueEnum messageType) {
        this.accountName = message.getAccountName();
        this.cardName = message.getCardName();
        this.amount = message.getAmount();
        this.header = new Header(message.getUserHandle(), authHandle);
        this.message = messageType.getValue();
        this.descriptor = message.getDescriptor();
        this.businessUuid = message.getBusinessUuid();
        this.processingType = message.getProcessingType() == null ? null : message.getProcessingType().getValue();
        this.sourceId = message.getSourceId();
        this.destinationId = message.getDestinationId();
    }
}
