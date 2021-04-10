package com.silamoney.clientrefactored.endpoints.accounts.getaccounts;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetAccountsRequest {
    
    private String userHandle;
    private String userPrivateKey;
    
}
