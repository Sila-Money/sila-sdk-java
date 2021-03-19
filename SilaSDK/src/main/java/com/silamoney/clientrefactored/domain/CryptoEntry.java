package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CryptoEntry {

    @SerializedName("crypto_alias")
    private String cryptoAlias;
    @SerializedName("crypto_address")
    private String cryptoAddress;
    @SerializedName("crypto_code")
    private final String cryptoCode = "ETH";
    
}
