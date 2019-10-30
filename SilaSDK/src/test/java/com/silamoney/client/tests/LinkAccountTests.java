package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.LinkAccountResponse;
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
public class LinkAccountTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host,
            DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200Success() throws Exception {
        ApiResponse response = api.LinkAccount(DefaultConfigurations.userHandle,
                "Custom Account Name", "public-xxx-xxx",
                DefaultConfigurations.userPrivateKey);

        assertEquals(200, response.getStatusCode());
        assertEquals("SUCCESS", ((LinkAccountResponse) response.getData()).getStatus());
    }

    @Test
    public void Response200Failure() throws Exception {
        ApiResponse response = api.LinkAccount("failure.silamoney.eth",
                "Custom Account Name", "public-xxx-xxx",
                DefaultConfigurations.userPrivateKey);

        assertEquals(200, response.getStatusCode());
        assertEquals("FAILURE", ((LinkAccountResponse) response.getData()).getStatus());
    }

    @Test(expected = BadRequestException.class)
    public void Response400() throws
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            IOException,
            InterruptedException,
            ForbiddenException {
        api.LinkAccount("badrequest.silamoney.eth",
                "Custom Account Name", "public-xxx-xxx",
                DefaultConfigurations.userPrivateKey);
    }

    @Test(expected = InvalidSignatureException.class)
    public void Response401() throws
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            IOException,
            InterruptedException,
            ForbiddenException {
        api.LinkAccount("invalidsignature.silamoney.eth",
                "Custom Account Name", "public-xxx-xxx",
                DefaultConfigurations.userPrivateKey);
    }
}
