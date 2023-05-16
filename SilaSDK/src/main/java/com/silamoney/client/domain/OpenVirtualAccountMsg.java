package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object sent in the link account method.
 *
 * @author Anuj
*/
public class OpenVirtualAccountMsg {


    @SerializedName("virtual_account_name")
    private final String virtualAccountName;

    @SerializedName("header")
    private final Header header;
    @SerializedName("ach_credit_enabled")
    private final Boolean achCreditEnabled;
    @SerializedName("ach_debit_enabled")
    private final Boolean achDebitEnabled;

    public OpenVirtualAccountMsg(String userHandle, String appHandle, String virtualAccountName, Boolean ahcCreditEnabled, Boolean achDebitEnabled,String reference) {
        this.virtualAccountName = virtualAccountName;
        this.header = new Header(userHandle, appHandle);
        this.header.setReference(reference);
        this.achCreditEnabled = ahcCreditEnabled;
        this.achDebitEnabled = achDebitEnabled;
    }
}
