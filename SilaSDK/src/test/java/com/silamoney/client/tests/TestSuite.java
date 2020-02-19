package com.silamoney.client.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.mockserver.integration.ClientAndServer;

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
    GetTransactionsTests.class/*,
    SilaBalanceTests.class*/
})
public class TestSuite {

    /*private static ClientAndServer mockServer;

    @BeforeClass
    public static void setUp() {
        mockServer = ClientAndServer.startClientAndServer(1080);
        MockServer.MockServer();
    }

    @AfterClass
    public static void tearDown() {
        mockServer.stop();
    }*/

}
