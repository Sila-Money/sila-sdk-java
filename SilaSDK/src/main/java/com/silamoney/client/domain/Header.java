package com.silamoney.client.domain;

import java.util.Date;
import java.util.UUID;

import com.google.gson.annotations.SerializedName;

/**
 * Object used in the Header msg.
 *
 * @author Karlo Lorenzana
 */
public class Header {

    private enum VersionEnum {
        ZERO_2("0.2"), V0_2("v0.2");

        private final String value;

        private VersionEnum(String value) {
            this.value = value;
        }
    }

    private enum CryptoEnum {
        ETH("ETH");

        private String value;

        private CryptoEnum(String value) {
            this.value = value;
        }
    }

    @SerializedName("reference")
    private final String reference;

    @SerializedName("created")
    private final Integer created;

    @SerializedName("user_handle")
    private final String userHandle;

    @SerializedName("app_handle")
    private final String authHandle;

    @SerializedName("version")
    private final String version;

    @SerializedName("crypto")
    private final String crypto;

    @SerializedName("business_handle")
    private String businessHandle;

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
        this.reference = UUID.randomUUID().toString();
        this.version = VersionEnum.ZERO_2.value;
    }
    /**
     * Constructor for header object.
     *
     * @param userHandle
     * @param appHandle
     * @param businessHandle
     */
    public Header(String userHandle, String appHandle,String businessHandle) {
        this.authHandle = appHandle;
        this.userHandle = userHandle;
        this.businessHandle=businessHandle;

        this.created = (int) (((new Date()).getTime() / 1000) - 100);
        this.crypto = CryptoEnum.ETH.value;
        this.reference = UUID.randomUUID().toString();
        this.version = VersionEnum.ZERO_2.value;
    }
}
