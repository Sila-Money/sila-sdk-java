package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Object sent in the Get Accounts method.
 *
 * @author Karlo Lorenzana
 */
public class GetAccountsMsg {

    @SerializedName("header")
    private final Header header;

    @SerializedName("message")
    private final String message;

    @SerializedName("search_filters")
    private final Map<String, String> searchFilters;

    /**
     * Constructor for GetAccountsMsg object.
     *
     * @param userHandle
     * @param appHandle
     * @param reference
     */

    public GetAccountsMsg(String userHandle, String appHandle, String reference) {
        this(userHandle, appHandle, reference, null);
    }

    /** Constructor with account number filter (optional). */
    public GetAccountsMsg(String userHandle, String appHandle, String reference, String accountNumberFilter) {
        this.header = new Header(userHandle, appHandle);
        this.header.setReference(reference);
        this.message = Message.ValueEnum.GET_ACCOUNTS_MSG.getValue();

        if (accountNumberFilter != null) {
            Map<String, String> f = new HashMap<>();
            f.put("account_number", accountNumberFilter);
            this.searchFilters = f;
        } else {
            this.searchFilters = null;
        }

    }
}
