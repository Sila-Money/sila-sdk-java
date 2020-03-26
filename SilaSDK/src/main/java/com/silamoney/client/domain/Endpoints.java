package com.silamoney.client.domain;

public enum Endpoints {
	CHECK_HANDLE("/check_handle"), 
	REGISTER("/register"), 
	REQUEST_KYC("/request_kyc"), 
	CHECK_KYC("/check_kyc"),
	LINK_ACCOUNT("/link_account"), 
	GET_ACCOUNTS("/get_accounts"), 
	ISSUE_SILA("/issue_sila"),
	TRANSFER_SILA("/transfer_sila"), 
	REDEEM_SILA("/redeem_sila"), 
	GET_TRANSACTIONS("/get_transactions"),
	GET_SILA_BALANCE("/get_sila_balance"),
	PLAID_SAMEDAY_AUTH("/plaid_sameday_auth"),
	GET_WALLET("/get_wallet"),
	REGISTER_WALLET("/register_wallet"),
	UPDATE_WALLET("/update_wallet"),
	DELETE_WALLET("/delete_wallet"),
	GET_WALLETS("/get_wallets");

	private String uri;

	private Endpoints(String uri) {
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}

}
