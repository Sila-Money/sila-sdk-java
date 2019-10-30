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
public class CheckHandleTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host,
            DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200Success() throws Exception {
        ApiResponse response = api.CheckHandle(DefaultConfigurations.userHandle);

        assertEquals(200, response.getStatusCode());
        assertEquals(DefaultConfigurations.userHandle + " is available.", ((BaseResponse) response.getData()).getMessage());
        assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
    }

    @Test
    public void Response200Failure() throws Exception {
        ApiResponse response = api.CheckHandle("taken.silamoney.eth");

        assertEquals(200, response.getStatusCode());
        assertEquals("taken.silamoney.eth is already taken.", ((BaseResponse) response.getData()).getMessage());
        assertEquals("FAILURE", ((BaseResponse) response.getData()).getStatus());
    }

    @Test(expected = BadRequestException.class)
    public void Response400() throws 
            BadRequestException, 
            InvalidSignatureException, 
            ServerSideException, 
            IOException, 
            InterruptedException,
            ForbiddenException  {
        api.CheckHandle("badrequest.silamoney.eth");
    }
    
    @Test(expected = InvalidSignatureException.class)
    public void Response401() throws 
            BadRequestException, 
            InvalidSignatureException, 
            ServerSideException, 
            IOException, 
            InterruptedException,
            ForbiddenException  {
        api.CheckHandle("invalidsignature.silamoney.eth");
    }
}
