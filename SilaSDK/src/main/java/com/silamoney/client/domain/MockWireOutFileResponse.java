package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class MockWireOutFileResponse extends BaseResponse {
    /**
     * String field used for the transaction id.
     */
    @Getter
    @SerializedName("transaction_id")
    private String transactionId;
    /**
     * String field used for the wire status.
     */
    @Getter
    @SerializedName("wire_status")
    private String wireStatus;
}
