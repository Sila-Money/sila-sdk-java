package com.silamoney.clientrefactored.endpoints.accounts.updateaccount;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateAccountRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String accountName;
    private String newAccountName;
    
}
