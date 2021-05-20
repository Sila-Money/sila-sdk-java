package com.silamoney.clientrefactored.endpoints.accounts.checkinstantach;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CheckInstantACHRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String accountName;
    
}
