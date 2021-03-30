package com.silamoney.clientrefactored.endpoints.entities.checkkyc;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CheckKycRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String kycLevel;
    
}
