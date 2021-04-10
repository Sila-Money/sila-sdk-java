package com.silamoney.clientrefactored.endpoints.accounts.plaidupdatelinktoken;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlaidUpdateLinkTokenRequest {
    
    private String userHandle;
    private String accountName;
    
}
