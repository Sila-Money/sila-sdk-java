package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class CertifyBeneficialOwnerMsg {
    @SerializedName("header")
    private final Header header;

    @SerializedName("member_handle")
    private String memberHandle;

    @SerializedName("certification_token")
    private final String certificationToken;

    public CertifyBeneficialOwnerMsg(String userHandle, String authHandle, String businessHandle, String memberHandle, String certificationToken) {
        this.header = new Header(userHandle, authHandle, businessHandle);
        this.memberHandle = memberHandle;
        this.certificationToken = certificationToken;
    }
}
