package com.silamoney.clientrefactored.endpoints.cards.deletecard;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeleteCardRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String cardName;
    private String provider;
    private String reference;
    
}
