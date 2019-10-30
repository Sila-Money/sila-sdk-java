package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetTransactionsResponse;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karlo Lorenzana
 */
public class GetTransactionsTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host,
            DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        ApiResponse response = api.GetTransactions(
                DefaultConfigurations.userHandle,
                DefaultConfigurations.filters,
                DefaultConfigurations.userPrivateKey
        );

        assertEquals(200, response.getStatusCode());
        assertTrue(((GetTransactionsResponse) response.getData()).success);
    }

    @Test(expected = BadRequestException.class)
    public void Response400() throws Exception {
        api.GetTransactions(
                "badrequest.silamoney.eth",
                DefaultConfigurations.filters,
                DefaultConfigurations.userPrivateKey
        );
    }

    @Test(expected = ForbiddenException.class)
    public void Response403() throws
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            IOException,
            InterruptedException,
            ForbiddenException {
        api.GetTransactions(
                "forbidden.silamoney.eth",
                DefaultConfigurations.filters,
                DefaultConfigurations.userPrivateKey
        );
    }

    @Test(expected = ServerSideException.class)
    public void Response401() throws
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            IOException,
            InterruptedException,
            ForbiddenException {
        api.GetTransactions(
                "serverside.silamoney.eth",
                DefaultConfigurations.filters,
                DefaultConfigurations.userPrivateKey
        );
    }
}
