package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object used in the entity msg.
 *
 * @author loren
 */
public class CryptoEntry {

    /**
     * String field used for the crypto alias.
     */
    @SerializedName("crypto_alias")
    public String cryptoAlias;

    private enum CryptoCodeEnum {
        ETH("ETH");

        private final String value;

        CryptoCodeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * String field used for the crypto address.
     */
    @SerializedName("crypto_address")
    public String cryptoAddress;

    /**
     * String field used for the crypto code.
     */
    @SerializedName("crypto_code")
    public String cryptoCode;

    /**
     * Constructor for the CryptoEntry object.
     *
     * @param user
     */
    public CryptoEntry(User user) {
        this.cryptoAddress = user.cryptoAddress;
        this.cryptoAlias = "";
        this.cryptoCode = CryptoCodeEnum.ETH.getValue();
    }
}
