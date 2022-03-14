package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Device {
    @SerializedName("device_fingerprint")
    private String deviceFingerPrint;

    @SerializedName("session_identifier")
    private String sessionIdentifier;
    public Device(String deviceFingerPrint){
        this.deviceFingerPrint=deviceFingerPrint;

    }
    public Device(String deviceFingerPrint,String sessionIdentifier){
        this.deviceFingerPrint=deviceFingerPrint;
        this.sessionIdentifier=sessionIdentifier;

    }
}
