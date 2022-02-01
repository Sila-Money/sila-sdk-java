package com.silamoney.clientrefactored.endpoints.virtualAccount.getVirtualAccounts;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetVirtualAccountsRequest {
    private String userHandle;
    private String userPrivateKey;
}
