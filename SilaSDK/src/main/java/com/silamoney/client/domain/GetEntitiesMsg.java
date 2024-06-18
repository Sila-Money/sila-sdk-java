package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class GetEntitiesMsg {
    @SerializedName("header")
    private final Header header;
    @SerializedName("entity_type")
    private final String entityType;
    @SerializedName("message")
    private final String message;
    public GetEntitiesMsg(String authHandle, String entityType,String reference) {
        this.header=new Header(null,authHandle);
        this.header.setReference(reference);
        this.entityType=entityType;
        this.message=Message.ValueEnum.HEADER_MSG.getValue();
    }
}
