package com.silamoney.clientrefactored.endpoints.entities.checkpartnerkyc;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CheckPartnerKycRequest {
    
    private String queryAppHandle;
    private String queryUserHandle;
    
}
