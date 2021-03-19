package com.silamoney.clientrefactored.endpoints.accounts.updateaccount;

import java.util.List;

import com.silamoney.clientrefactored.domain.Account;
import com.silamoney.clientrefactored.domain.Change;

import lombok.Getter;

@Getter
public class UpdateAccountResponse {

    private boolean success;
    private String message;
    private String status;
    private Account account;
    private List<Change> changes;

}
