package com.silamoney.clientrefactored.endpoints.virtualAccount.getVirtualAccount;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetVirtualAccountRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String virtualAccountId;
    
}
