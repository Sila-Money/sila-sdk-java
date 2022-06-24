package com.silamoney.clientrefactored.endpoints.accounts.updateaccount;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.Account;
import com.silamoney.clientrefactored.domain.BaseResponse;
import com.silamoney.clientrefactored.domain.Change;

import lombok.Getter;

@Getter
public class UpdateAccountResponse extends BaseResponse {

    private Account account;
    private List<Change> changes;
}
