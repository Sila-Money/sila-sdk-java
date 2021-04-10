package com.silamoney.clientrefactored.endpoints.wallets.registerwallet;

import com.silamoney.clientrefactored.domain.Wallet;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterWalletRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String walletVerificationSignature;
    private Wallet wallet;
    
}
