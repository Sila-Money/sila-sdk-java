package com.silamoney.client.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CheckPartnerKycRequest {
    
    private String queryAppHandle;
    private String queryUserHandle;
    
}
