package com.silamoney.clientrefactored.endpoints.accounts.getaccounts;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetAccountsRequest {
    
    private String userHandle;
    private String userPrivateKey;
    
}
