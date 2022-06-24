package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Certification {
    
    @SerializedName("administrator_user_handle")
    private String administratorUserHandle;
    private String created;
    @SerializedName("created_epoch")
    private String createdEpoch;
    @SerializedName("expires_after")
    private String expiresAfter; 
    @SerializedName("expires_after_epoch")
    private String expiresAfterEpoch;
    @SerializedName("beneficial_owner_certifications")
    private List<String> beneficialOwnerCertifications;  

}
