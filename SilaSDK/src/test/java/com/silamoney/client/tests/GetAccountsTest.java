package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.Account;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;
import java.io.IOException;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.mockserver.integration.ClientAndServer;

/**
 *
 * @author Karlo Lorenzana
 */
public class GetAccountsTest {

    SilaApi api = new SilaApi(DefaultConfigurations.host,
            DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    private static ClientAndServer mockServer;

    @BeforeClass
    public static void setUp() {
        mockServer = ClientAndServer.startClientAndServer(1080);
        MockServer.MockServer();
    }

    @AfterClass
    public static void tearDown() {
        mockServer.stop();
    }

    @Test
    public void Response200Success() throws Exception {
        ApiResponse response = api.GetAccounts(DefaultConfigurations.userHandle,
                DefaultConfigurations.userPrivateKey);

        assertEquals(200, response.statusCode);
        assertEquals(1, ((List<Account>) response.data).size());
    }

    @Test(expected = BadRequestException.class)
    public void Response400() throws
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            IOException,
            InterruptedException {
        api.GetAccounts("badrequest.silamoney.eth",
                DefaultConfigurations.userPrivateKey);
    }

    @Test(expected = InvalidSignatureException.class)
    public void Response401() throws
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            IOException,
            InterruptedException {
        api.LinkAccount("invalidsignature.silamoney.eth",
                "Custom Account Name", "public-xxx-xxx",
                DefaultConfigurations.userPrivateKey);
    }
}
