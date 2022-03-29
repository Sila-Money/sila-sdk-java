package com.silamoney.clientrefactored.endpoints.virtualAccount.openVirtualAccount;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OpenVirtualAccountRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String virtualAccountName;
    private Boolean achCreditEnabled;
    private Boolean achDebitEnabled;
}
