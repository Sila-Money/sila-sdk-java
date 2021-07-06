package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Wallet {

    @SerializedName("blockchain_address")
    private String blockChainAddress;
    @SerializedName("blockchain_network")
    private String blockChainNetwork;
    @SerializedName("nickname")
    private String nickname;
    private transient String privateKey;
    @SerializedName("default")
    private boolean defaultWallet;

}