package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 * DeleteWalletTests
 * 
 * @author wzelada
 */
public class DeleteWalletTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    String userHandle = DefaultConfigurations.getUserHandle();
    String userPrivateKey = DefaultConfigurations.getUserPrivateKey();

    @Test
    public void Response200() throws Exception {

        ApiResponse response = api.deleteWallet(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getNewWallet().getPrivateKey());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
            InterruptedException, ForbiddenException {

        ApiResponse response = api.deleteWallet("", DefaultConfigurations.getUserPrivateKey());
        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void Response403() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
            InterruptedException, ForbiddenException {

        api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                "3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
        ApiResponse response = api.deleteWallet(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey());
        assertEquals(403, response.getStatusCode());
    }
}