package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.*;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import static org.junit.Assert.*;

public class TearDownTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle, DefaultConfigurations.privateKey);

    @Test
    public void Response200Success() throws Exception {
        ApiResponse response = api.getEntity(DefaultConfigurations.getUserHandle(), DefaultConfigurations.getUserPrivateKey());
        assertEquals(200, response.getStatusCode());
        GetEntityResponse entityResponse = (GetEntityResponse) response.getData();
        if (!entityResponse.getPhones().isEmpty()) {
            for (Phone phone : entityResponse.getPhones()) {
                DeleteRegistrationMessage message = DeleteRegistrationMessage.builder()
                        .userHandle(DefaultConfigurations.getUserHandle())
                        .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
                        .uuid(phone.getUuid()).build();
                response = api.deleteRegistrationData(RegistrationDataEnum.PHONE, message);
                assertEquals(200, response.getStatusCode());
                BaseResponse parsedResponse = (BaseResponse) response.getData();
                assertTrue(parsedResponse.getSuccess());
                assertEquals("SUCCESS", parsedResponse.getStatus());
            }
        }
        if (!entityResponse.getAddresses().isEmpty()) {
            for (Address address : entityResponse.getAddresses()) {
                DeleteRegistrationMessage message = DeleteRegistrationMessage.builder()
                        .userHandle(DefaultConfigurations.getUserHandle())
                        .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
                        .uuid(address.getUuid()).build();
                response = api.deleteRegistrationData(RegistrationDataEnum.ADDRESS, message);
                assertEquals(403, response.getStatusCode());
                BaseResponse parsedResponse = (BaseResponse) response.getData();
                assertFalse(parsedResponse.getSuccess());
                assertEquals("FAILURE", parsedResponse.getStatus());
            }
        }


        DeleteRegistrationMessage  message = DeleteRegistrationMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
                .uuid(entityResponse.getEmails().get(0).getUuid()).build();
        response = api.deleteRegistrationData(RegistrationDataEnum.EMAIL, message);
        assertEquals(200, response.getStatusCode());
        BaseResponse  parsedResponse = (BaseResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());

    }
   }

