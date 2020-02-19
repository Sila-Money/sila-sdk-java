package com.silamoney.client.domain;

/**
 * Object sent in silaBalance endpoint.
 *
 * @author Karlo Lorenzana
 */
public class SilaBalanceMsg {

    private final String address;

    /**
     * Constructor for SilaBalanceMsg.
     *
     * @param address
     */
    public SilaBalanceMsg(String address) {
        this.address = address;
    }

	public String getAddress() {
		return address;
	}

}
