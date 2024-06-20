package com.silamoney.clientrefactored.endpoints.accounts.plaidupdatelinktoken;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlaidUpdateLinkTokenRequest {
    
    private String userHandle;
    private String accountName;
    private String reference;
    
}
