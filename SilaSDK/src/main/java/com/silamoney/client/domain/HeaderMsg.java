package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object used as body in sila api calls.
 *
 * @author Karlo Lorenzana
 */
public class HeaderMsg {

    /**
     * Header object used in the header msg.
     */
    @SerializedName("header")
    public Header header;

    /**
     * String property used for the message field.
     */
    @SerializedName("message")
    public String message;

    /**
     * HeaderMsg constructor.
     *
     * @param userHandle
     * @param appHandle
     */
    public HeaderMsg(String userHandle, String appHandle) {
        this.header = new Header(userHandle, appHandle);
        this.message = Message.ValueEnum.HEADER_MSG.getValue();
    }
}
