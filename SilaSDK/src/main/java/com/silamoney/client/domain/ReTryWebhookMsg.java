package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class ReTryWebhookMsg {
    @SerializedName("header")
    private final Header header;
    @SerializedName("message")
    private final String message;
    @SerializedName("eventUuid")
    private final String eventUuid;

    public ReTryWebhookMsg(String userHandle, String appHandle, String eventUuid) {
        this.header = new Header(userHandle, appHandle);
        this.message = Message.ValueEnum.HEADER_MSG.getValue();
        this.eventUuid = eventUuid;
    }
}
