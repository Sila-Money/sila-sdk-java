package com.silamoney.client.domain;

public enum Endpoints {
    CHECK_HANDLE("/check_handle"), REGISTER("/register"), REQUEST_KYC("/request_kyc"), CHECK_KYC("/check_kyc"),
    LINK_ACCOUNT("/link_account"), GET_ACCOUNTS("/get_accounts"), GET_ACCOUNT_BALANCE("/get_account_balance"),
    ISSUE_SILA("/issue_sila"), TRANSFER_SILA("/transfer_sila"), REDEEM_SILA("/redeem_sila"),
    GET_TRANSACTIONS("/get_transactions"), GET_SILA_BALANCE("/get_sila_balance"),
    PLAID_SAMEDAY_AUTH("/plaid_sameday_auth"), GET_WALLET("/get_wallet"), REGISTER_WALLET("/register_wallet"),
    UPDATE_WALLET("/update_wallet"), DELETE_WALLET("/delete_wallet"), GET_WALLETS("/get_wallets"),
    GET_BUSINESS_TYPES("/get_business_types"), GET_BUSINESS_ROLES("/get_business_roles"),
    GET_NAICS_CATEGORIES("/get_naics_categories"), LINK_BUSINESS_MEMBER("/link_business_member"),
    UNLINK_BUSINESS_MEMBER("/unlink_business_member"), GET_ENTITY("/get_entity"),
    CERTIFY_BENEFICIAL_OWNER("/certify_beneficial_owner"), CERTIFY_BUSINESS("/certify_business"),
    GET_ENTITIES("/get_entities"), GET_DOCUMENT_TYPES("/document_types"), CANCEL_TRANSACTION("/cancel_transaction"),
    DELETE_REGISTRATION("/delete"), DOCUMENTS("/documents"), LIST_DOCUMENTS("/list_documents"),
    GET_DOCUMENT("/get_document"), ADD_REGISTRATION_DATA("/add"), UPDATE_REGISTRATION_DATA("/update"),
    PLAID_LINK_TOKEN("/plaid_link_token"),DELETE_ACCOUNT("/delete_account"),LINK_CARD("/link_card"),DELETE_CARD("/delete_card"),
    GET_CARDS("/get_cards"),GET_WEBHOOKS("/get_webhooks"),REVERSE_TRANSACTION("/reverse_transaction"),
    GET_PAYMENT_METHODS("/get_payment_methods"),OPEN_VIRTUAL_ACCOUNT("/open_virtual_account"),
    GET_VIRTUAL_ACCOUNTS("/get_virtual_accounts"), GET_VIRTUAL_ACCOUNT("/get_virtual_account"),
    UPDATE_VIRTUAL_ACCOUNT("/update_virtual_account"),RETRY_WEBHOOK("/retry_webhook"),
    CLOSE_VIRTUAL_ACCOUNT("/close_virtual_account"),CREATE_TEST_VIRTUAL_ACCOUNT_ACH_TRANSACTION("/create_test_virtual_account_ach_transaction"),
    CHECK_INSTANT_ACH("/check_instant_ach"),GET_INSTITUTIONS("/get_institutions"),PLAID_UPDATE_LINK_TOKEN("/plaid_update_link_token"),
    UPDATE_ACCOUNT("/update_account"),CHECK_PARTNER_KYC("/check_partner_kyc"),APPROVE_WIRE("/approve_wire"),MOCK_WIRE_OUT_FILE("/mock_wire_out_file"),
    GET_WALLET_STATEMENT_DATA("/get_wallet_statement_data"), GET_STATEMENTS_DATA("/get_statements_data"), GET_STATEMENT_TRANSACTIONS("/get_statement_transactions"),STATEMENTS("/statements"),
    REFUND_DEBIT_CARD("/refund_debit_card"),CREATE_CKO_TESTING_TOKEN("/create_cko_testing_token"),GET_VERIFICATIONS("/get_verifications"),RESUME_VERIFICATION("/resume_verification"),
    UPDATE_ID_DOCUMENT("/update/id_document");

    private String uri;

    private Endpoints(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

}
