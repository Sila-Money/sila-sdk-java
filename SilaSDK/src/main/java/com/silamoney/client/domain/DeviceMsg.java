package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class DeviceMsg {
    @SerializedName("header")
    private Header header;
    @SerializedName("device_fingerprint")
    private String deviceFingerPrint;

    @SerializedName("session_identifier")
    private String sessionIdentifier;

    public DeviceMsg(String authHandle, UserHandleMessage user, Device device) {
        this.header = new Header(user.getUserHandle(), authHandle);
        this.header.setReference(user.getReference());
        this.deviceFingerPrint = device.getDeviceFingerPrint();
        this.sessionIdentifier = device.getSessionIdentifier();
    }
}
