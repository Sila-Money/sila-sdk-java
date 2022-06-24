package com.silamoney.clientrefactored.endpoints.transactions.reversetransaction;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.BaseResponse;
import com.silamoney.clientrefactored.domain.Pagination;
import com.silamoney.clientrefactored.domain.Transaction;

import lombok.Getter;

@Getter
public class ReverseTransactionResponse extends BaseResponse {
    @SerializedName("error_code")
    private String errorCode;

}

