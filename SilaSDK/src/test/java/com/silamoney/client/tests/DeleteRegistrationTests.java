package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.DeleteRegistrationMessage;
import com.silamoney.client.domain.GetEntityResponse;
import com.silamoney.client.domain.RegistrationDataEnum;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class DeleteRegistrationTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200Success() throws Exception {
        ApiResponse response = api.getEntity(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey());
        assertEquals(200, response.getStatusCode());
        GetEntityResponse entityResponse = (GetEntityResponse) response.getData();
        DeleteRegistrationMessage message = DeleteRegistrationMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
                .uuid(entityResponse.getIdentities().get(0).getUuid()).build();
        response = api.deleteRegistrationData(RegistrationDataEnum.IDENTITY, message);
        assertEquals(200, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertThat("delete registration - message", parsedResponse.getMessage(), stringContainsInOrder(
                Arrays.asList(String.format("Successfully deleted %s", RegistrationDataEnum.IDENTITY.getUri()))));
    }
}
