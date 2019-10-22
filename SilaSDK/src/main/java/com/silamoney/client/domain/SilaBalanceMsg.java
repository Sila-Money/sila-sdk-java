package com.silamoney.client.domain;

/**
 * Object sent in silaBalance endpoint.
 *
 * @author Karlo Lorenzana
 */
public class SilaBalanceMsg {

    /**
     * String field used for addess.
     */
    public String address;

    /**
     * Constructor for SilaBalanceMsg.
     *
     * @param address
     */
    public SilaBalanceMsg(String address) {
        this.address = address;
    }

}
