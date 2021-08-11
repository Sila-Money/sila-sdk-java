package com.silamoney.clientrefactored.endpoints.entities.requestkyc;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RequestKycRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String kycLevel;
    
}
