package com.silamoney.clientrefactored.endpoints.paymentMethods;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.BaseResponse;
import com.silamoney.clientrefactored.domain.Pagination;
import com.silamoney.clientrefactored.domain.PaymentMethods;
import lombok.Getter;

import java.util.List;

@Getter
public class GetPaymentMethodsResponse extends BaseResponse {

    private Pagination pagination;
    @SerializedName("payment_methods")
    private List<PaymentMethods> paymentMethods;
}

