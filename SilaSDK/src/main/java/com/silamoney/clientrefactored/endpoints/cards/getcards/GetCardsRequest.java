package com.silamoney.clientrefactored.endpoints.cards.getcards;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetCardsRequest {
    
    private String userHandle;
    private String userPrivateKey;
    
}
