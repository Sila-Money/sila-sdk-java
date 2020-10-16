package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.AddressMessage;
import com.silamoney.client.domain.AddressResponse;
import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.EmailMessage;
import com.silamoney.client.domain.EmailResponse;
import com.silamoney.client.domain.IdentityMessage;
import com.silamoney.client.domain.IdentityResponse;
import com.silamoney.client.domain.PhoneMessage;
import com.silamoney.client.domain.PhoneResponse;
import com.silamoney.client.domain.UserHandleMessage;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class AddRegistrationDataTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200Email() throws Exception {
        UserHandleMessage user = UserHandleMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
        EmailMessage message = EmailMessage.builder().email("some_new_email@domain.sila").build();
        ApiResponse response = api.addEmail(user, message);
        assertEquals(200, response.getStatusCode());
        EmailResponse parsedResponse = (EmailResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertThat(parsedResponse.getMessage(), stringContainsInOrder(Arrays.asList("Successfully added email")));
        assertThat(parsedResponse.getEmail(), notNullValue());
        DefaultConfigurations.setEmailUuid(parsedResponse.getEmail().getUuid());
        assertThat(parsedResponse.getEmail().getAddedEpoch(), notNullValue());
        assertThat(parsedResponse.getEmail().getModifiedEpoch(), notNullValue());
        assertThat(parsedResponse.getEmail().getUuid(), notNullValue());
        assertThat(parsedResponse.getEmail().getEmail(), notNullValue());
    }

    @Test
    public void Response200Phone() throws Exception {
        UserHandleMessage user = UserHandleMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
        PhoneMessage message = PhoneMessage.builder().phone("1234567890").build();
        ApiResponse response = api.addPhone(user, message);
        assertEquals(200, response.getStatusCode());
        PhoneResponse parsedResponse = (PhoneResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertThat(parsedResponse.getMessage(), stringContainsInOrder(Arrays.asList("Successfully added phone")));
        assertThat(parsedResponse.getPhone(), notNullValue());
        DefaultConfigurations.setPhoneUuid(parsedResponse.getPhone().getUuid());
        assertThat(parsedResponse.getPhone().getAddedEpoch(), notNullValue());
        assertThat(parsedResponse.getPhone().getModifiedEpoch(), notNullValue());
        assertThat(parsedResponse.getPhone().getUuid(), notNullValue());
        assertThat(parsedResponse.getPhone().getPhone(), notNullValue());
    }

    @Test
    public void Response200Identity() throws Exception {
        UserHandleMessage user = UserHandleMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
        IdentityMessage message = IdentityMessage.builder().identityAlias("SSN").identityValue("123452222").build();
        ApiResponse response = api.addIdentity(user, message);
        assertEquals(200, response.getStatusCode());
        IdentityResponse parsedResponse = (IdentityResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertThat(parsedResponse.getMessage(), stringContainsInOrder(Arrays.asList("Successfully added identity")));
        assertThat(parsedResponse.getIdentity(), notNullValue());
        DefaultConfigurations.setIdentityUuid(parsedResponse.getIdentity().getUuid());
        assertThat(parsedResponse.getIdentity().getAddedEpoch(), notNullValue());
        assertThat(parsedResponse.getIdentity().getModifiedEpoch(), notNullValue());
        assertThat(parsedResponse.getIdentity().getUuid(), notNullValue());
        assertThat(parsedResponse.getIdentity().getIdentityType(), notNullValue());
        assertThat(parsedResponse.getIdentity().getIdentity(), notNullValue());
    }

    @Test
    public void Response200Address() throws Exception {
        UserHandleMessage user = UserHandleMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
        AddressMessage message = AddressMessage.builder().addressAlias("new address")
                .streetAddress1("324 Songbird Avenue").streetAddress2("Apt. 132").city("Portland").state("VA")
                .country("US").postalCode("12345").build();
        ApiResponse response = api.addAddress(user, message);
        assertEquals(200, response.getStatusCode());
        AddressResponse parsedResponse = (AddressResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertThat(parsedResponse.getMessage(), stringContainsInOrder(Arrays.asList("Successfully added address")));
        assertThat(parsedResponse.getAddress(), notNullValue());
        DefaultConfigurations.setAddressUuid(parsedResponse.getAddress().getUuid());
        assertThat(parsedResponse.getAddress().getAddedEpoch(), notNullValue());
        assertThat(parsedResponse.getAddress().getModifiedEpoch(), notNullValue());
        assertThat(parsedResponse.getAddress().getUuid(), notNullValue());
        assertThat(parsedResponse.getAddress().getNickname(), notNullValue());
        assertThat(parsedResponse.getAddress().getStreetAddress1(), notNullValue());
        assertThat(parsedResponse.getAddress().getStreetAddress2(), notNullValue());
        assertThat(parsedResponse.getAddress().getCity(), notNullValue());
        assertThat(parsedResponse.getAddress().getState(), notNullValue());
        assertThat(parsedResponse.getAddress().getCountry(), notNullValue());
        assertThat(parsedResponse.getAddress().getPostalCode(), notNullValue());
    }

    @Test
    public void Response400() throws Exception {
        UserHandleMessage user = UserHandleMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
        EmailMessage message = EmailMessage.builder().build();
        ApiResponse response = api.addEmail(user, message);
        assertEquals(400, response.getStatusCode());
        BadRequestResponse parsedResponse = (BadRequestResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
        assertThat(parsedResponse.getMessage(), stringContainsInOrder(Arrays.asList("Bad request")));
    }

    @Test
    public void Response403() throws Exception {
        SilaApi badApi = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                "3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
        UserHandleMessage user = UserHandleMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
        EmailMessage message = EmailMessage.builder().email("some_other_email@domain.sila").build();
        ApiResponse response = badApi.addEmail(user, message);
        assertEquals(403, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
        assertThat(parsedResponse.getMessage(),
                stringContainsInOrder(Arrays.asList("Failed to authenticate app signature.")));
    }
}
