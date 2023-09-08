package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Setter;

/**
 * Object sent in the link account method.
 *
 * @author Karlo Lorenzana
*/
@Setter
public class LinkCardMsg {

    @SerializedName("token")
    private final String token;

    @SerializedName("card_name")
    private final String cardName;

    @SerializedName("header")
    private final Header header;

    @SerializedName("message")
    private final String message;

    @SerializedName("account_postal_code")
    private String accountPostalCode;

    /*
    String field used for provider
    */
    @SerializedName("provider")
    private final String provider;

    /*
    Boolean field used for skip_verification
    */
    @SerializedName("skip_verification")
    private final Boolean skipVerification;

    /**
     * Constructor for LinkAccountMsg object.
     *
     * @param userHandle
     * @param token
     * @param cardName
     * @param accountPostalCode
     * @param appHandle
     */
    public LinkCardMsg(String userHandle, String token, String cardName,
                         String accountPostalCode, String appHandle,String reference,String provider,Boolean skipVerification) {
        this.token = token;
        this.cardName = cardName;
        this.accountPostalCode = accountPostalCode;
        this.header = new Header(userHandle, appHandle);
        this.header.setReference(reference);
        this.message = Message.ValueEnum.HEADER_MSG.getValue();
        this.provider=provider;
        this.skipVerification=skipVerification;
    }
}
