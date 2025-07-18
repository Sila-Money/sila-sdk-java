package com.silamoney.client.domain;

/**
 * Contains the message possible options.
 *
 * @author Karlo Lorenzana
 */
public class Message {
    
    private Message(){
        
    }

    /**
     * Enum that contains the message options.
     */
    public enum ValueEnum {

        /**
         * String value for HeaderMsg.
         */
        HEADER_MSG("header_msg"),
        /**
         * String value for EntityMsg.
         */
        ENTITY_MSG("entity_msg"),
        /**
         * String value for GetAccountMsg.
         */
        GET_ACCOUNTS_MSG("get_accounts_msg"),
        /**
         * String value for GetAccountMsg.
         */
        GET_ACCOUNT_BALANCE_MSG("get_account_balance_msg"),
        /**
         * String value for GetTransactionsMsg.
         */
        GET_TRANSACTIONS_MSG("get_transactions_msg"),
        /**
         * String value for IssueMsg.
         */
        ISSUE_MSG("issue_msg"),
        /**
         * String value for LinkAccountMsg.
         */
        LINK_ACCOUNT_MSG("link_account_msg"),
        /**
         * String value for RedeemMsg.
         */
        REDEEM_MSG("redeem_msg"),
        /**
         * String value for TransferMsg.
         */
        TRANSFER_MSG("transfer_msg"),
        /**
         * String value for PlaidSameDayAuthMsg.
         */
        PLAID_SAMEDAY_AUTH_MSG("plaid_sameday_auth_msg"),
        /**
         * String value for GetWalletMsg.
         */
        GET_WALLET_MSG("get_wallet_msg"),
        /**
         * String value for RegisterWalletMsg.
         */
        REGISTER_WALLET_MSG("register_wallet_msg"),
        /**
         * String value for UpdateWalletMsg.
         */
        UPDATE_WALLET_MSG("update_wallet_msg"),
        /**
         * String value for DeleteWalletMsg.
         */
        DELETE_WALLET_MSG("delete_wallet_msg"),
        /**
         * String value for GetWalletsMsg.
         */
        GET_WALLETS_MSG("get_wallets_msg"),
        /**
         * String value for GetSilaBalanceMsg.
         */
        GET_SILA_BALANCE("get_sila_balance"),
        /**
         * String value for GetBusinessTypes.
         */
        GET_BUSINESS_TYPES("get_business_types"), 
        /**
         * String value for GetBusinessRoles.
         */
        GET_BUSINESS_ROLES("get_business_roles"), 
        /**
         * String value for GetNaicsCategories.
         */
        GET_NAICS_CATEGORIES_MSG("get_naics_categories"), 
        /**
         * String value for LinkBusinessMember.
         */
        LINK_BUSINESS_MEMBER_MSG("link_business_member"), 
        /**
         * String value for check kyc.
         */
        CHECK_KYC("check_kyc"),
        /**
         * String value for get verifications.
         */
        GET_VERIFICATIONS_MSG("get_verifications"),
        /**
         * String value for ResumeVerification.
         */
        RESUME_VERIFICATION_MSG("resume_verification"),
        /**
         * String value for UnlinkBusinessMember.
         */
        UNLINK_BUSINESS_MEMBER_MSG("unlink_business_member"), 
        /**
         * String value for GetEntity.
         */
        GET_ENTITY_MSG("get_entity"), 
        /**
         * String value for certifyBeneficialOwner.
         */
        CERTIFY_BENEFICIAL_OWNER("certify_business_owner"), 
        /**
         * String value for CertifyBusiness.
         */
        CERTIFY_BUSINESS("certify_business"), 
        /**
         * String value for GetEntities.
         */
        GET_ENTITIES("get_entities"),
        /**
         * String value for PlaidLinkToken.
         */
        PLAID_LINK_TOKEN("plaid_link_token"),
        /**
         * String value for DeleteAccount.
         */
        DELETE_ACCOUNT("delete_account"),
        /**
         * String value for RegisterBusiness.
         */
        REGISTER_BUSINESS("register_business"),
        /**
         * String value for LinkCardMsg.
         */
        LINK_CARD_MSG("link_card_msg"),
        /**
         * String value for DeleteCard.
         */
        DELETE_CARD("delete_card"),
        /**
         * String value for GetCards.
         */
        GET_CARD_MSG("get_cards"),
        /**
         * String value for GetWebhooks.
         */
        GET_WEBHOOKS("get_webhooks"),
        /**
         * String value for Request KYC.
         */
        REQUEST_KYC("request_kyc"),
        /**
         * String value for GetPaymentMethods.
         */
        GET_PAYMENT_METHODS("get_payment_methods"),
        /**
         * String value for OpenVirtualAccount.
         */
        OPEN_VIRTUAL_ACCOUNT("open_virtual_account"),
        /**
         * String value for GetVirtualAccounts.
         */
        GET_VIRTUAL_ACCOUNTS("get_virtual_accounts"),
        /**
         * String value for GetVirtualAccount.
         */
        GET_VIRTUAL_ACCOUNT("get_virtual_account"),
        /**
         * String value for UpdateVirtualAccount.
         */
        UPDATE_VIRTUAL_ACCOUNT("update_virtual_account"),
        /**
         * String value for retryWebhook.
         */
        RETRY_WEBHOOK("retryWebhook"),
        /**
         * String value for CloseVirtualAccount.
         */
        CLOSE_VIRTUAL_ACCOUNT("close_virtual_account"),
        /**
         * String value for CreateTestVirtualAccountAchTransaction.
         */
        CREATE_TEST_VIRTUAL_ACCOUNT_ACH_TRANSACTION("create_test_virtual_account_ach_transaction"),
        /**
         * String value for checkPartnerKyc.
         */
        CHECK_PARTNER_KYC("check_partner_kyc"),
        /**
         * String value for UpdateAccount.
         */
        UPDATE_ACCOUNT("update_account"),
        /**
         * String value for PlaidUpdateLinkToken.
         */
        PLAID_UPDATE_LINK_TOKEN("plaid_update_link_token"),
        /**
         * String value for CheckInstantAch.
         */
        CHECK_INSTANT_ACH("check_instant_ach"),
        /**
         * String value for ReverseTransationMsg.
         */
        REVERSE_TRANSACTION_MSG("reverse_transaction_msg"),
        /**
         * String value for ApproveWireMsg.
         */
        APPROVE_WIRE_MSG("approve_wire_msg"),
        /**
         * String value for MockWireOutFile.
         */
        MOCK_WIRE_OUT_FILE("mock_wire_out_file"),
        /**
         * String value for GetWalletStatementData.
         */
        GET_WALLET_STATEMENT_DATA_MSG("get_wallet_statement_data_msg"),
        /**
         * String value for GetStatementsData.
         */
        GET_STATEMENTS_DATA_MSG("get_statements_data_msg"),
        /**
         * String value for GetStatementTransactions.
         */
        GET_STATEMENT_TRANSACTIONS_MSG("get_statement_transactions_msg"),
        /**
         * String value for resendStatements.
         */
        RESEND_STATEMENTS_MSG("resend_statements_msg"),
        /**
         * String value for statements.
         */
        STATEMENTS_MSG("statements_msg"),
        /**
         * String value for create cko testing token.
         */
        CREATE_CKO_TESTING_TOKEN_MSG("create_cko_testing_token_msg"),
        /**
         * String value for UpdateIdDocumentMsg.
         */
        UPDATE_ID_DOCUMENT_MSG("update_id_document_msg");


        private final String value;

        ValueEnum(String value) {
            this.value = value;
        }

        /**
         * Gets the selected message value.
         *
         * @return option value.
         */
        public String getValue() {
            return value;
        }
    }
}
