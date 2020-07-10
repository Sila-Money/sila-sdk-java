package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.CheckKYCResponse;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 *
 * @author Karlo Lorenzana
 */
public class CheckKYCTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    String userHandle2Failure = "javasdk-349425739";
    String userPrivateKeyFailure = "f6406f347993b09ee3760e8ef0fb70abdeaa90265dc02de78f86da5eff9b6272";

    @Test
    public void Response200() throws Exception {
        // KYCID2
        ApiResponse response = api.checkKYC(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey());

        assertEquals(200, response.getStatusCode());
        while (!((CheckKYCResponse) response.getData()).getStatus().contains("SUCCESS")) {
            TimeUnit.SECONDS.sleep(5);
            response = api.checkKYC(DefaultConfigurations.getUserHandle(), DefaultConfigurations.getUserPrivateKey());
        }
        assertEquals("SUCCESS", ((CheckKYCResponse) response.getData()).getStatus());

        response = api.checkKYC(DefaultConfigurations.getUser2Handle(), DefaultConfigurations.getUser2PrivateKey());

        assertEquals(200, response.getStatusCode());
        while (!((CheckKYCResponse) response.getData()).getStatus().contains("SUCCESS")) {
            TimeUnit.SECONDS.sleep(5);
            response = api.checkKYC(DefaultConfigurations.getUser2Handle(), DefaultConfigurations.getUser2PrivateKey());
        }
        assertEquals("SUCCESS", ((CheckKYCResponse) response.getData()).getStatus());

        response = api.checkKYC(DefaultConfigurations.getBusinessHandle(),
                DefaultConfigurations.getBusinessPrivateKey());

        assertEquals(200, response.getStatusCode());
        while (!((CheckKYCResponse) response.getData()).getMessage().contains("Business has passed verification")) {
            TimeUnit.SECONDS.sleep(5);
            response = api.checkKYC(DefaultConfigurations.getBusinessHandle(),
                    DefaultConfigurations.getBusinessPrivateKey());
        }

        assertTrue(((CheckKYCResponse) response.getData()).getMessage().contains("Business has passed verification"));
    }

    @Test
    public void Response200Failure() throws Exception {
        // KYCID3
        ApiResponse response = api.checkKYC(userHandle2Failure, userPrivateKeyFailure);
        assertEquals(200, response.getStatusCode());
        assertEquals("FAILURE", ((CheckKYCResponse) response.getData()).getStatus());
    }

    @Test
    public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
            InterruptedException, ForbiddenException {
        ApiResponse response = api.checkKYC("", DefaultConfigurations.getUserPrivateKey());
        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void Response401User() throws BadRequestException, InvalidSignatureException, ServerSideException,
            IOException, InterruptedException, ForbiddenException {
        api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                "3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");

        ApiResponse response = api.checkKYC(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey());
        assertEquals(401, response.getStatusCode());
    }
}
