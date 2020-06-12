package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class AccountBalanceResponse extends BaseResponse {

	@SerializedName("available_balance")
	private int availableBalance;

	@SerializedName("current_balance")
	private int currentBalance;

	@SerializedName("masked_account_number")
	private String maskedAccountNumber;

	@SerializedName("routing_number")
	private String routingNumber;

	@SerializedName("account_name")
	private String accountName;

	/**
	 * Gets the response available balance.
	 * 
	 * @return availableBalance
	 */
	public int getAvailableBalance() {
		return availableBalance;
	}

	/**
	 * Gets the response current balance.
	 * 
	 * @return currentBalance
	 */
	public int getCurrentBalance() {
		return currentBalance;
	}

	/**
	 * Gets the response masked account number.
	 * 
	 * @return maskedAccountNumber
	 */
	public String getMaskedAccountNumber() {
		return maskedAccountNumber;
	}

	/**
	 * Gets the response routing number.
	 * 
	 * @return routingNumber
	 */
	public String getRoutingNumber() {
		return routingNumber;
	}

	/**
	 * Gets the response account name.
	 * 
	 * @return accountName
	 */
	public String getAccountName() {
		return accountName;
	}

}
