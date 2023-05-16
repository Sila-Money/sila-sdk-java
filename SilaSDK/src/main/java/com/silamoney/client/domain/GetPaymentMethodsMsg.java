package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class GetPaymentMethodsMsg {
    @SerializedName("header")
    private final Header header;

    @SerializedName("search_filters")
    private final PaymentMethodsSearchFilters searchFilters;


    /**
     * Constructor for GetPaymentMethodsMsg object.
     *
     * @param userHandle
     * @param appHandle
     * @param searchFilters
     * @param reference
     */
    public GetPaymentMethodsMsg(String userHandle, String appHandle,
                                PaymentMethodsSearchFilters searchFilters,String reference) {
        this.searchFilters = searchFilters;
        this.header = new Header(userHandle, appHandle);
        this.header.setReference(reference);
    }
}
