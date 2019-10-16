package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 *
 * @author Karlo Lorenzana
 */
public class Header {

    /**
     * String property used for reference field
     */
    @SerializedName("reference")
    public String reference;

    /**
     * Integer property used for the created field
     */
    @SerializedName("created")
    public Integer created;

    /**
     * String property used for user handle field.
     */
    @SerializedName("user_handle")
    public String userHandle;

    @SerializedName("auth_handle")
    public String authHandle = null;

    public enum VersionEnum {
        _0_2("0.2"),
        V0_2("v0.2");

        public String value;

        VersionEnum(String value) {
            this.value = value;
        }
    }

    /**
     * Enum property used for the version field.
     */
    @SerializedName("version")
    public String version;

    /**
     * Enum used to set possible crypto options.
     */
    public enum CryptoEnum {
        ETH("ETH");

        public String value;

        CryptoEnum(String value) {
            this.value = value;
        }
    }

    /**
     * Enum property used for crypto field.
     */
    @SerializedName("crypto")
    public String crypto;

    /**
     * Constructor for header object.
     *
     * @param userHandle
     * @param appHandle
     */
    public Header(String userHandle, String appHandle) {
        this.authHandle = appHandle;
        this.userHandle = userHandle;

        this.created = (int) (((new Date()).getTime() / 1000) - 100);
        this.crypto = CryptoEnum.ETH.value;
        this.reference = "ref";
        this.version = VersionEnum._0_2.value;
    }
}
