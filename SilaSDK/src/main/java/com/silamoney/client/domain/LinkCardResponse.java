package com.silamoney.client.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Object used to map Link Account response.
 *
 * @author Karlo Lorenzana
 */
@Getter
public class LinkCardResponse extends BaseResponse {
    @Expose
    @SerializedName(value = "account_name")
    private String accountName;
    @Expose
    @SerializedName(value = "card_name")
    private String cardName;
    @Expose
    @SerializedName(value = "avs")
    private String avs;
    @Expose
    @SerializedName(value ="card_details")
    private Card cardDetails;

}
