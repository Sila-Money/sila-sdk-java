package com.silamoney.clientrefactored.endpoints.entities.requestkyc;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RequestKycRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String kycLevel;
    
}
