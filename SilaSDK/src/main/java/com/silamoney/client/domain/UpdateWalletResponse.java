package com.silamoney.client.domain;

import java.util.List;

import lombok.Getter;

@Getter
public class UpdateWalletResponse extends BaseResponse {
    
    private Wallet wallet;
    private List<Change> changes;

}
