package com.silamoney.clientrefactored.endpoints.accounts.checkinstantach;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CheckInstantACHRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String accountName;
    private String kycLevel;
}
