package com.silamoney.clientrefactored.endpoints.paymentMethods;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.Pagination;
import com.silamoney.clientrefactored.domain.PaymentMethods;
import lombok.Getter;

import java.util.List;

@Getter
public class GetPaymentMethodsResponse {

    private boolean success;
    private String status;
    private String message;
    private Pagination pagination;
    @SerializedName("payment_methods")
    private List<PaymentMethods> paymentMethods;
    private String reference;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}

