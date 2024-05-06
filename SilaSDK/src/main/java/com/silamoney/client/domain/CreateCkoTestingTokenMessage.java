package com.silamoney.client.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
public class CreateCkoTestingTokenMessage {
    @Getter
    private String userHandle;
    @Getter
    private String userPrivateKey;
    @Getter
    private String cardNumber;
    @Getter
    private int expiryMonth;
    @Getter
    private int expiryYear;
    @Getter
    private int cvv;
    @Getter
    private String ckoPublicKey;
    @Getter
    private String reference;

}
