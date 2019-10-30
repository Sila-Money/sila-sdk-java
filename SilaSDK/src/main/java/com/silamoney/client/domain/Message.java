package com.silamoney.client.domain;

/**
 * Contains the message possible options.
 *
 * @author Karlo Lorenzana
 */
public class Message {
    
    private Message(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * Enum that contains the message options.
     */
    public static enum ValueEnum {

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
        TRANSFER_MSG("transfer_msg");

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
