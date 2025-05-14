package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class RemoteAccountDetails {
    @Getter
    @SerializedName("account_number")
    private String accountNumber;
    @Getter
    @SerializedName("routing_number")
    private String routingNumber;
    @Getter
    @SerializedName("wire_account_number")
    private String wireAccountNumber;
    @Getter
    @SerializedName("wire_routing_number")
    private String wireRoutingNumber;

    public RemoteAccountDetails(String accountNumber, String routingNumber, String wireAccountNumber, String wireRoutingNumber) {
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
        this.wireAccountNumber = wireAccountNumber;
        this.wireRoutingNumber = wireRoutingNumber;
    }

}
