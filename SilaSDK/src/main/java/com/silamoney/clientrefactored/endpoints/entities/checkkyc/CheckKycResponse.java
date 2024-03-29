package com.silamoney.clientrefactored.endpoints.entities.checkkyc;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.BaseResponse;
import com.silamoney.clientrefactored.domain.Certification;
import com.silamoney.clientrefactored.domain.Member;
import com.silamoney.clientrefactored.domain.Verification;

import lombok.Getter;

@Getter
public class CheckKycResponse extends BaseResponse {

    @SerializedName("entity_type")
    private String entityType;
    @SerializedName("verification_status")
    private String verificationStatus;
    @SerializedName("valid_kyc_levels")
    private List<String> validKycLevels;
    @SerializedName("certification_status")
    private String certificationStatus;
    @SerializedName("verification_history")
    private List<Verification> verificationHistory;
    @SerializedName("certification_history")
    private List<Certification> certificationHistory;
    private List<Member> members;
}
