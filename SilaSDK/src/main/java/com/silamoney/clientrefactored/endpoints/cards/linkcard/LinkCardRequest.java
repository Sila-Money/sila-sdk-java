package com.silamoney.clientrefactored.endpoints.cards.linkcard;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LinkCardRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String token;
    private String cardName;
    private String accountPostalCode;

}
