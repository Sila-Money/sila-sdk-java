package com.silamoney.client.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * @author Karlo Lorenzana
 */
@RunWith(Suite.class)
@SuiteClasses({
    CheckHandleTests.class,
    RegisterTests.class,
    RequestKYCTests.class,
    CheckKYCTests.class,
    LinkAccountTests.class,
    GetAccountsTests.class,
    IssueSilaTests.class,
    TransferSilaTests.class, 
    RedeemSilaTests.class,
    GetTransactionsTests.class,
    PlaidSameDayAuthTests.class,
    RegisterWalletTests.class,
    GetWalletTests.class,
    UpdateWalletTests.class,
    DeleteWalletTests.class,
    GetWalletsTests.class
})
public class TestSuite {}
