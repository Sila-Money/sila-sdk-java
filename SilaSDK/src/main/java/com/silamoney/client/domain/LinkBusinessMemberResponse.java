package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class LinkBusinessMemberResponse extends LinkBusinessOperationResponse {
    private String details;
    @SerializedName(value = "verification_uuid")
    private String verificationUuid;

    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @return the verificationUuid
     */
    public String getVerificationUuid() {
        return verificationUuid;
    }
}