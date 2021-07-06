package com.silamoney.clientrefactored.endpoints.wallets.updatewallet;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateWalletRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String nickname;
    private boolean defaultWallet;
    
}
