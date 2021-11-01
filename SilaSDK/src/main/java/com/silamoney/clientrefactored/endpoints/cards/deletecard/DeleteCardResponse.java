package com.silamoney.clientrefactored.endpoints.cards.deletecard;

import lombok.Getter;

@Getter
public class DeleteCardResponse {

    private boolean success;
    private String reference;
    private String message;
    private String status;

}
