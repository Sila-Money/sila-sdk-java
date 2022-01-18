package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Response used for the majority of endpoints.
 *
 * @author Karlo Lorenzana
 */
public class TransactionResponse extends BaseResponse {
    
	@SerializedName("transaction_id")
    private String transactionId;
    
    private String descriptor;
	@Getter
	@SerializedName("error_code")
	private String errorCode;
    /**
     * Gets the response transaction.
     * @return transaction id
     */
	public String getTransactionId() {
		return transactionId;
	}

	/**
     * Gets the response descriptor.
     * @return descriptor
     */
	public String getDescriptor() {
		return descriptor;
	}
    
    
}
