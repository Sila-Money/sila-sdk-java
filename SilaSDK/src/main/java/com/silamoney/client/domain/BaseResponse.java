package com.silamoney.client.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Response used for the majority of endpoints.
 *
 * @author Karlo Lorenzana
 */
public class BaseResponse {

    @Getter
    private String reference;

    @Getter
    private String message;

    @Getter
    private String status;

    @Getter
    @Setter
    private Boolean success;
}
