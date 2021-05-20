package com.silamoney.clientrefactored.endpoints.accounts.linkaccount;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LinkAccountRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String plaidToken;
    private String accountName;
    private String selectedAccountId;
    private String accountNumber;
    private String routingNumber;
    private String accountType;
    private String plaidTokenType;
    
}
