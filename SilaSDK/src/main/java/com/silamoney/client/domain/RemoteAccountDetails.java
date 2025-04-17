package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class RemoteAccountDetails {

    @SerializedName("account_number")
    @Getter
    private String accountNumber;

    @SerializedName("routing_number")
    @Getter
    private String routingNumber;

    @SerializedName("wire_account_number")
    @Getter
    private String wireAccountNumber;

    @Getter
    @SerializedName("wire_routing_number")
    private String wireRoutingNumber;
}
