package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTestVirtualAccountAchTransactionMsg {

    @SerializedName("header")
    private final Header header;
    @SerializedName("amount")
    private final int amount;
    @SerializedName("virtual_account_number")
    private final String virtualAccountNumber;
    @SerializedName("tran_code")
    private final int tranCode;
    @SerializedName("effective_date")
    private final String effectiveDate;
    @SerializedName("entity_name")
    private final String entityName;
    @SerializedName("ced")
    private final String ced;
    @SerializedName("ach_name")
    private final String achName;


    public CreateTestVirtualAccountAchTransactionMsg(String userHandle, String authHandle, int amount, String virtualAccountNumber, Date date, int tranCode, String entityName, String ced, String achName) {
        this.header = new Header(userHandle, authHandle);
        this.amount = amount;
        this.virtualAccountNumber = virtualAccountNumber;
        this.tranCode = tranCode;
        if (date != null) {
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String effectiveDate = simpleDateFormat.format(date);
            this.effectiveDate = effectiveDate;
        } else
            this.effectiveDate = null;
        this.entityName = entityName;
        this.ced = ced != null ? ced : null;
        this.achName = achName != null ? achName : null;

    }
}
