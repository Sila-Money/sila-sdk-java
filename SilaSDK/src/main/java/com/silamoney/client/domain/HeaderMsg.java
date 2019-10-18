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

    @SerializedName("message")
    private final String message;

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
