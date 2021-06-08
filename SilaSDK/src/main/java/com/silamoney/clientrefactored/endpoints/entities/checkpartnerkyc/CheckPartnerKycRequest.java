package com.silamoney.clientrefactored.endpoints.entities.checkpartnerkyc;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CheckPartnerKycRequest {
    
    private String queryAppHandle;
    private String queryUserHandle;
    
}
