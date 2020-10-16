package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class HeaderBase {
    @SerializedName("auth_handle")
    private final String authHandle;
    @SerializedName("created")
    private final Integer created;
    @SerializedName("user_handle")
    private final String userHandle;
    @SerializedName("reference")
    private final String reference;
    @SerializedName("version")
    private final String version;
    @SerializedName("crypto")
    private final String crypto;

    HeaderBase(String authHandle, Integer created, String userHandle, String reference, String version, String crypto) {
        this.authHandle = authHandle;
        this.created = created;
        this.userHandle = userHandle;
        this.reference = reference;
        this.version = version;
        this.crypto = crypto;
    }
}
