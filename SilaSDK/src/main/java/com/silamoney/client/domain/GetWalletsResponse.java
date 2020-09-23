package com.silamoney.client.domain;

import java.util.List;

import lombok.Getter;

public class GetWalletsResponse extends PaginationInformation {

    @Getter
    private boolean success;
    @Getter
    private List<Wallet> wallets;
    
}