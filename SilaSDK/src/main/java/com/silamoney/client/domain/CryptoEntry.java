package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object used in the entity msg.
 *
 * @author loren
 */
public class CryptoEntry {
    
    private enum CryptoCodeEnum {
        ETH("ETH");

        private final String value;

        CryptoCodeEnum(String value) {
            this.value = value;
        }

        private String getValue() {
            return value;
        }
    }

    @SerializedName("crypto_alias")
    private String cryptoAlias;

    @SerializedName("crypto_address")
    private final String cryptoAddress;

    @SerializedName("crypto_code")
    private final String cryptoCode;

    /**
     * Constructor for the CryptoEntry object.
     *
     * @param user
     */
    public CryptoEntry(User user) {
        this.cryptoAddress = user.getCryptoAddress();
        this.cryptoAlias = "";
        this.cryptoCode = CryptoCodeEnum.ETH.getValue();
    }

    /**
     * Constructor for the CryptoEntry object.
     *
     * @param user
     */
    public CryptoEntry(BusinessUser user) {
        this.cryptoAddress = user.getCryptoAddress();
        this.cryptoCode = CryptoCodeEnum.ETH.getValue();
    }
}
