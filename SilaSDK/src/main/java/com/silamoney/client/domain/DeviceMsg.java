package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class DeviceMsg {
    @SerializedName("header")
    private HeaderBase header;
    @SerializedName("device_fingerprint")
    private String deviceFingerPrint;

    public DeviceMsg(String authHandle, UserHandleMessage user, Device device) {
        this.header = new HeaderBuilder(authHandle).withUserHandle(user.getUserHandle()).withReference().build();
        this.deviceFingerPrint = device.getDeviceFingerPrint();
    }
}
