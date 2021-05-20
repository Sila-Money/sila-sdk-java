package com.silamoney.clientrefactored.endpoints.wallets.updatewallet;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateWalletRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String nickname;
    private boolean defaultWallet;
    
}
