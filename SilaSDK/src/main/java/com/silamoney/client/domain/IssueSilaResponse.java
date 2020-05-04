package com.silamoney.client.domain;

/**
 * Response used for the majority of endpoints.
 *
 * @author Karlo Lorenzana
 */
public class IssueSilaResponse {

    private String reference;

    private String message;

    private String status;
    
    private String transactionId;
    
    private String descriptor;

    /**
     * Gets the response reference.
     * @return reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * Gets the response message.
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the response status.
     * @return status
     */
    public String getStatus() {
        return status;
    }

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
