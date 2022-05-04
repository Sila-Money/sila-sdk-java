package com.silamoney.clientrefactored.endpoints.wallets.updatewallet;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.BaseResponse;
import com.silamoney.clientrefactored.domain.Change;
import com.silamoney.clientrefactored.domain.Wallet;

import lombok.Getter;

@Getter
public class UpdateWalletResponse extends BaseResponse {

    @SerializedName("wallet")
    private Wallet wallet;
    private List<Change> changes;
}
