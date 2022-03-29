package com.silamoney.clientrefactored.endpoints.virtualAccount.closeVirtualAccount;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CloseVirtualAccountRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String virtualAccountId;
    private String accountNumber;
}
