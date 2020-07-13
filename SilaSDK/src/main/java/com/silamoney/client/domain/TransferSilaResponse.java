package com.silamoney.client.domain;

/**
 * Response used for the majority of endpoints.
 *
 * @author Karlo Lorenzana
 */
public class TransferSilaResponse extends TransactionResponse {

    private String destinationAddress;

    /**
     * Gets the response destination address.
     * @return reference
     */
    public String getDestinationAddress() {
        return destinationAddress;
    }
    
}
