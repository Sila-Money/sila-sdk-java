package com.silamoney.client.domain;

/**
 * Contains the message possible options.
 *
 * @author Karlo Lorenzana
 */
public class Message {

    /**
     * Enum that contains the message options.
     */
    public static enum ValueEnum {
        HEADER_MSG("header_msg"),
        ENTITY_MSG("entity_msg"),
        GET_ACCOUNTS_MSG("get_accounts_msg"),
        GET_TRANSACTIONS_MSG("get_transactions_msg"),
        ISSUE_MSG("issue_msg"),
        LINK_ACCOUNT_MSG("link_account_msg"),
        REDEEM_MSG("redeem_msg"),
        TRANSFER_MSG("transfer_msg");

        private String value;

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
