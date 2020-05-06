package com.silamoney.client.domain;

/**
 * Response used for the majority of endpoints.
 *
 * @author Karlo Lorenzana
 */
public class TransferSilaResponse {

    private String reference;

    private String message;

    private String status;
    
    private String descriptor;
    
    private String businessUuid;

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
     * Gets the response descriptor.
     * @return descriptor
     */
	public String getDescriptor() {
		return descriptor;
	}

	/**
     * Gets the response business uuid.
     * @return business uuid
     */
	public String getBusinessUuid() {
		return businessUuid;
	}
    
    
}
