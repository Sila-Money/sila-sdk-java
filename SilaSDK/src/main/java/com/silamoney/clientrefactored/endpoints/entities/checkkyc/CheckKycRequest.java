package com.silamoney.clientrefactored.endpoints.entities.checkkyc;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CheckKycRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String kycLevel;
    
}
