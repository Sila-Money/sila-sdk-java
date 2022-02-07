package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class GetPaymentMethodsResponse extends BaseResponse {
    @SerializedName("payment_methods")
    private List<PaymentMethods> paymentMethods;
    private Pagination pagination;

}
