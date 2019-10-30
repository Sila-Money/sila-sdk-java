package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
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
public class RequestKYCTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host,
            DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        ApiResponse response = api.RequestKYC(DefaultConfigurations.userHandle, DefaultConfigurations.userPrivateKey);

        assertEquals(200, response.getStatusCode());
        assertEquals(DefaultConfigurations.userHandle + " submitted for KYC review", ((BaseResponse) response.getData()).getMessage());
        assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
    }

    @Test(expected = BadRequestException.class)
    public void Response400() throws
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            IOException,
            InterruptedException,
            ForbiddenException {
        api.RequestKYC("badrequest.silamoney.eth", DefaultConfigurations.userPrivateKey);
    }

    @Test(expected = InvalidSignatureException.class)
    public void Response401() throws
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            IOException,
            InterruptedException,
            ForbiddenException {
        api.RequestKYC("invalidsignature.silamoney.eth", DefaultConfigurations.userPrivateKey);
    }
}
