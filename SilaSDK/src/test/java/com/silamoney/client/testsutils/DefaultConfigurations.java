package com.silamoney.client.testsutils;

import com.silamoney.client.domain.SearchFilters;
import java.util.ArrayList;

/**
 * Sets the default configuration values for testing.
 *
 * @author Karlo Lorenzana
 */
public class DefaultConfigurations {

    /**
     * Default host for testing.
     */
    public static String host = "http://localhost:1080";

    /**
     * Default appHandle for testing.
     */
    public static String appHandle = "handle.silamoney.eth";

    /**
     * Default private key for testing.
     */
    public static String privateKey = "0CF475AD4A6C4B1E34915A3AF40E62621AC73DD"
            + "752969473B6B6031A8E03021D";

    /**
     * Default user handle for testing.
     */
    public static String userHandle = "user.silamoney.eth";

    /**
     * Default user private key for testing.
     */
    public static String userPrivateKey = "0CF475AD4A6C4B1E34915A3AF40E62621AC73DD"
            + "752969473B6B6031A8E03021D";

    /**
     * Default search filters for testing.
     */
    public static SearchFilters filters = new SearchFilters()
            .setTransactionId("some UUID string assigned by Sila")
            .setReferenceId("the reference string sent in the header object when transaction request was made")
            .showTimelines()
            .setMaxSilaAmount(1300)
            .setMinSilaAmount(1000)
            .setStatuses(new ArrayList<>() {
                {
                    add(SearchFilters.StatusesEnum.PENDING);
                    add(SearchFilters.StatusesEnum.SUCCESSFUL);
                    add(SearchFilters.StatusesEnum.FAILED);
                    add(SearchFilters.StatusesEnum.COMPLETE);
                }
            })
            .setStartEpoch(1234567860)
            .setEndEpoch(1234567891)
            .setPage(1)
            .setPerPage(20)
            .setTransactionTypes(new ArrayList<>() {
                {
                    add(SearchFilters.TransactionTypesEnum.ISSUE);
                    add(SearchFilters.TransactionTypesEnum.REDEEM);
                    add(SearchFilters.TransactionTypesEnum.TRANSFER);
                }
            });
}
