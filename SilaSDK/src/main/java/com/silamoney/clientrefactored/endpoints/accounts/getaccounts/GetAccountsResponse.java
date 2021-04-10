package com.silamoney.clientrefactored.endpoints.accounts.getaccounts;

import java.util.List;

import com.silamoney.clientrefactored.domain.Account;

import lombok.Getter;

@Getter
public class GetAccountsResponse {

    private List<Account> accounts;

    public GetAccountsResponse(List<Account> accounts) {
        this.accounts = accounts;
    }

}
