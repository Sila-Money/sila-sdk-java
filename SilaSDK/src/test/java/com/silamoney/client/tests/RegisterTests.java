package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.User;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;
import java.io.IOException;
import java.util.Date;
import org.joda.time.LocalDate;
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
public class RegisterTests {

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
    public void Response200() throws Exception {
        LocalDate birthdate = new LocalDate(1900, 01, 31);
        User user = new User("user.silamoney.eth", 
                "Example", 
                "User", 
                "123 Main Street", 
                null, 
                "New City", 
                "OR", 
                "97204-1234", 
                "503-123-4567", 
                "example@silamoney.com", 
                "123452222", 
                "0x1234567890abcdef1234567890abcdef12345678", 
                birthdate.toDate());
        
        ApiResponse response = api.Register(user);

        assertEquals(response.statusCode, 200);
        assertEquals(((BaseResponse) response.data).message, "user.silamoney.eth was successfully registered ");
        assertEquals(((BaseResponse) response.data).status, "SUCCESS");
    }

    @Test(expected = BadRequestException.class)
    public void Response400() throws 
            BadRequestException, 
            InvalidSignatureException, 
            ServerSideException, 
            IOException, 
            InterruptedException  {
        LocalDate birthdate = new LocalDate(1900, 01, 31);
        User user = new User("badrequest.silamoney.eth", 
                "Example", 
                "User", 
                "123 Main Street", 
                null, 
                "New City", 
                "OR", 
                "97204-1234", 
                "503-123-4567", 
                "example@silamoney.com", 
                "123452222", 
                "0x1234567890abcdef1234567890abcdef12345678", 
                birthdate.toDate());
        
        api.Register(user);
    }
    
    @Test(expected = InvalidSignatureException.class)
    public void Response401() throws 
            BadRequestException, 
            InvalidSignatureException, 
            ServerSideException, 
            IOException, 
            InterruptedException  {
        LocalDate birthdate = new LocalDate(1900, 01, 31);
        User user = new User("invalidsignature.silamoney.eth", 
                "Example", 
                "User", 
                "123 Main Street", 
                null, 
                "New City", 
                "OR", 
                "97204-1234", 
                "503-123-4567", 
                "example@silamoney.com", 
                "123452222", 
                "0x1234567890abcdef1234567890abcdef12345678", 
                birthdate.toDate());
        
        api.Register(user);
    }
}
