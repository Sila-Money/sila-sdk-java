package com.silamoney.clientrefactored.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Verification {
    
    @SerializedName("verification_id")
    private String verificationId;
    @SerializedName("verification_status")
    private String verificationStatus;
    @SerializedName("kyc_level")
    private String kycLevel;
    @SerializedName("requested_at")
    private Long requestAt;
    @SerializedName("updated_at")
    private Long updatedAt;
    private List<String> reasons;
    private List<String> tags;
    private Double score;
    @SerializedName("parent_verification")
    private Object parentVerification;
    @SerializedName("valid_kyc_levels")
    private List<String> validKycLevels;

}
