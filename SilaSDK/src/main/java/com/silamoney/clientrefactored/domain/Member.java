package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Member {
    
    @SerializedName("user_handle")
    private String userHandle;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    private String role;
    private String details;
    @SerializedName("ownership_stake")
    private Float ownerShipStake;
    @SerializedName("verification_status")
    private String verificationStatus;
    @SerializedName("verification_required")
    private boolean verificationRequired;
    @SerializedName("verification_id")
    private String verificationId;
    @SerializedName("beneficial_owner_certification_status")
    private String beneficialOwnerCertificationStatus;
    @SerializedName("business_certification_status")
    private String businessCertificationStatus;

}
