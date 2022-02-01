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

    public OpenVirtualAccountMsg(String userHandle,
                                 String appHandle,  String virtualAccountName) {
        this.virtualAccountName = virtualAccountName;
        this.header = new Header(userHandle, appHandle);
    }
}
