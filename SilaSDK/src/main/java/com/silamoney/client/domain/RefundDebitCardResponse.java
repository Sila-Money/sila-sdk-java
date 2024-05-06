package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Response used for the '/refund_debit_card' endpoint.
 *
 * @author Anuj Kalal
 */
@Getter
public class RefundDebitCardResponse extends BaseResponse {
    /**
     * String field used for the transaction id.
     */
    @SerializedName("transaction_id")
    private String transactionId;

    /**
     * String field used for the refund transaction id.
     */
    @SerializedName("refund_transaction_id")
    private String refundTransactionId;

    /**
     * String field used for the warning.
     */
    private String warning;

}
