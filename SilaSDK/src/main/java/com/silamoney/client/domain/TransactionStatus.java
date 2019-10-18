package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object used in transaction timeline.
 *
 * @author Karlo Lorenzana
 */
public class TransactionStatus {

    /**
     * String field used for the date.
     */
    @SerializedName("date")
    public String date;

    /**
     * Integer field used for the date epoch.
     */
    @SerializedName("date_epoch")
    public Integer dateEpoch;

    /**
     * String field used for the status.
     */
    @SerializedName("status")
    public String status;

    /**
     * String field used for the usd status.
     */
    @SerializedName("usd_status")
    public String usdStatus;

    /**
     * String field used for the token status.
     */
    @SerializedName("token_status")
    public String tokenStatus;
}
