package com.silamoney.clientrefactored.endpoints.virtualAccount.updateVirtualAccount;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateVirtualAccountRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private final String virtualAccountId;
    private String virtualAccountName;
    private Boolean active;
    private Boolean achCreditEnabled;
    private Boolean achDebitEnabled;
    
}
