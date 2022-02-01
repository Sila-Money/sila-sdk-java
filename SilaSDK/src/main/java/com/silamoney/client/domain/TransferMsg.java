package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object sent in the transfer sila method.
 *
 * @author loren
 */
public class TransferMsg {

    @SerializedName("amount")
    private final int amount;

    @SerializedName("destination")
    private final String destination;

    @SerializedName("header")
    private final Header header;

    @SerializedName("message")
    private final String message;

    @SerializedName("destination_address")
    private final String destinationAddress;
    
    @SerializedName("descriptor")
    private final String descriptor;
    
    @SerializedName("business_uuid")
    private final String businessUuid;

    @SerializedName("source_id")
    private String sourceId;

    @SerializedName("destination_id")
    private String destinationId;


    /**
     ** Constructor for TransferMsg object.
     *
     * @param userHandle
     * @param destination
     * @param amount
     * @param descriptor
     * @param businessUuid
     * @param appHandle
     * @param sourceId
     * @param destinationId
     */
    public TransferMsg(String userHandle,
            String destination,
            int amount,
            String destinationAddress,
            String descriptor, 
            String businessUuid, 
            String appHandle,String sourceId,String destinationId) {
        this.amount = amount;
        this.destination = destination;
        this.destinationAddress = destinationAddress;
        this.header = new Header(userHandle, appHandle);
        this.message = Message.ValueEnum.TRANSFER_MSG.getValue();
        this.descriptor = descriptor;
        this.businessUuid = businessUuid;
        this.sourceId = sourceId;
        this.destinationId = destinationId;;
    }
}
