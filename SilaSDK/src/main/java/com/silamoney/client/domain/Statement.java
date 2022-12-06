package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;
@Getter
public class Statement {

    @SerializedName("user_handle")
    private String userHandle;
    @SerializedName("date")
    private String date;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("wallet_id")
    private String walletId;
    @SerializedName("beginning_balance")
    private String beginningBalance;
    @SerializedName("ending_balance")
    private String endingBalance;


    @SerializedName("transactions")
    private List<TransactionEntity> transactions;

}
