package com.silamoney.clientrefactored.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Certification {
    
    @SerializedName("administrator_user_handle")
    private String administratorUserHandle;
    private String created;
    @SerializedName("created_epoch")
    private Long createdEpoch;
    @SerializedName("expires_after")
    private String expiresAfter; 
    @SerializedName("expires_after_epoch")
    private Long expiresAfterEpoch;
    @SerializedName("beneficial_owner_certifications")
    private List<String> beneficialOwnerCertifications;  

}
