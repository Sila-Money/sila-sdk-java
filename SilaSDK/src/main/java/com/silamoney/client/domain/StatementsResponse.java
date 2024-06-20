package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

/**
 * Response used for the '/statements' endpoint.
 *
 * @author Anuj Kalal
 */
@Getter
public class StatementsResponse extends BaseResponse{
    /**
     * List<Statement> used for array of documents.
     */
    @SerializedName("delivery_attempts")
    private List<DeliveryAttempts> deliveryAttempts;

    /**
     * Pagination Entity used for the pagination.
     */
    private Pagination pagination;
}
