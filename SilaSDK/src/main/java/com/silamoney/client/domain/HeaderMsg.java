package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object used as body in sila api calls.
 *
 * @author Karlo Lorenzana
 */
public class HeaderMsg {

    @SerializedName("header")
    private final Header header;

    @SerializedName("kyc_level")
    
    private final String kycLevel;

    @SerializedName("message")
    private final String message;

    /**
     * HeaderMsg constructor.
     *
     * @param userHandle
     * @param appHandle
     */
    public HeaderMsg(String userHandle, String kycLevel, String appHandle,String reference) {
        this.kycLevel = kycLevel;
        this.header = new Header(userHandle, appHandle);
        this.header.setReference(reference);
        this.message = Message.ValueEnum.HEADER_MSG.getValue();
    }

        /**
     * HeaderMsg constructor.
     *
     * @param userHandle
     * @param appHandle
     */
    public HeaderMsg(String userHandle, String appHandle,String reference) {
        this.kycLevel = null;
        this.header = new Header(userHandle, appHandle);
        this.header.setReference(reference);
        this.message = Message.ValueEnum.HEADER_MSG.getValue();
    }
}
