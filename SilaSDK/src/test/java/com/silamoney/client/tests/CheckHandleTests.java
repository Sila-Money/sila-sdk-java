package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.junit.MockServerRule;

/**
 *
 * @author Karlo Lorenzana
 */
public class CheckHandleTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host,
            DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    private ClientAndServer mockServer;

    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this);

    @Before
    public void setUp() {
        mockServer = ClientAndServer.startClientAndServer(1080);
        MockServer.MockServer();
    }

    @After
    public void tearDown() {
        mockServer.stop();
    }

    @Test
    public void Response200Success() throws Exception {
        ApiResponse response = api.CheckHandle("user.silamoney.eth");

        assertEquals(response.statusCode, 200);
        assertEquals(((BaseResponse) response.data).message, "user.silamoney.eth is available.");
        assertEquals(((BaseResponse) response.data).status, "SUCCESS");
    }

    @Test
    public void Response200Failure() throws Exception {
        ApiResponse response = api.CheckHandle("taken.silamoney.eth");

        assertEquals(response.statusCode, 200);
        assertEquals(((BaseResponse) response.data).message, "taken.silamoney.eth is already taken.");
        assertEquals(((BaseResponse) response.data).status, "FAILURE");
    }

    @Test(expected = BadRequestException.class)
    public void Response400() throws 
            BadRequestException, 
            InvalidSignatureException, 
            ServerSideException, 
            IOException, 
            InterruptedException  {
        api.CheckHandle("badrequest.silamoney.eth");
    }
    
    @Test(expected = InvalidSignatureException.class)
    public void Response401() throws 
            BadRequestException, 
            InvalidSignatureException, 
            ServerSideException, 
            IOException, 
            InterruptedException  {
        api.CheckHandle("invalidsignature.silamoney.eth");
    }
}
