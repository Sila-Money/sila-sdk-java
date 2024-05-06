package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Response used for the '/create_cko_testing_token' endpoint.
 *
 * @author Anuj Kalal
 */
@Getter
public class CreateCkoTestingTokenResponse extends BaseResponse {
    /**
     * String field used for the token.
     */
    @SerializedName("token")
    private String token;

}
