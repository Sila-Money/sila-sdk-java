package com.silamoney.client.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlaidUpdateLinkTokenRequest {
    
    private String userHandle;
    private String accountName;
    
}
