package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.AddressMessage;
import com.silamoney.client.domain.AddressResponse;
import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.Device;
import com.silamoney.client.domain.DeviceResponse;
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
                assertTrue(parsedResponse.getMessage().contains("Successfully added email"));
                assertNotEquals(parsedResponse.getEmail(), null);
                DefaultConfigurations.setEmailUuid(parsedResponse.getEmail().getUuid());
                assertNotEquals(parsedResponse.getEmail().getAddedEpoch(), null);
                assertNotEquals(parsedResponse.getEmail().getModifiedEpoch(), null);
                assertNotEquals(parsedResponse.getEmail().getUuid(), null);
                assertNotEquals(parsedResponse.getEmail().getEmail(), null);
        }

        @Test
        public void Response200Phone() throws Exception {
                UserHandleMessage user = UserHandleMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
                PhoneMessage message = PhoneMessage.builder().phone("1234567890").smsOptIn(true).build();
                ApiResponse response = api.addPhone(user, message);
                assertEquals(200, response.getStatusCode());
                PhoneResponse parsedResponse = (PhoneResponse) response.getData();
                assertTrue(parsedResponse.getSuccess());
                assertEquals("SUCCESS", parsedResponse.getStatus());
                assertTrue(parsedResponse.getMessage().contains("Successfully added phone"));
                assertNotEquals(null, parsedResponse.getPhone());
                DefaultConfigurations.setPhoneUuid(parsedResponse.getPhone().getUuid());
                assertNotEquals(null, parsedResponse.getPhone().getAddedEpoch());
                assertNotEquals(null, parsedResponse.getPhone().getModifiedEpoch());
                assertNotEquals(null, parsedResponse.getPhone().getUuid());
                assertNotEquals(null, parsedResponse.getPhone().getPhone());
        }

        @Test
        public void Response200Identity() throws Exception {
                UserHandleMessage user = UserHandleMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
                IdentityMessage message = IdentityMessage.builder().identityAlias("SSN").identityValue("123452383")
                                .build();
                ApiResponse response = api.addIdentity(user, message);
                assertEquals(200, response.getStatusCode());
                IdentityResponse parsedResponse = (IdentityResponse) response.getData();
                assertTrue(parsedResponse.getSuccess());
                assertEquals("SUCCESS", parsedResponse.getStatus());
                assertTrue(parsedResponse.getMessage().contains("Successfully added identity"));
                assertNotEquals(null, parsedResponse.getIdentity());
                DefaultConfigurations.setIdentityUuid(parsedResponse.getIdentity().getUuid());
                assertNotEquals(null, parsedResponse.getIdentity().getAddedEpoch());
                assertNotEquals(null, parsedResponse.getIdentity().getModifiedEpoch());
                assertNotEquals(null, parsedResponse.getIdentity().getUuid());
                assertNotEquals(null, parsedResponse.getIdentity().getIdentityType());
                assertNotEquals(null, parsedResponse.getIdentity().getIdentity());
        }

        @Test
        public void Response200Address() throws Exception {
                UserHandleMessage user = UserHandleMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
                AddressMessage message = AddressMessage.builder().addressAlias("new address")
                                .streetAddress1("324 Songbird Avenue").streetAddress2("Apt. 132").city("Portland")
                                .state("VA").country("US").postalCode("12345").build();
                ApiResponse response = api.addAddress(user, message);
                assertEquals(200, response.getStatusCode());
                AddressResponse parsedResponse = (AddressResponse) response.getData();
                assertTrue(parsedResponse.getSuccess());
                assertEquals("SUCCESS", parsedResponse.getStatus());
                assertTrue(parsedResponse.getMessage().contains("Successfully added address"));
                assertNotEquals(null, parsedResponse.getAddress());
                DefaultConfigurations.setAddressUuid(parsedResponse.getAddress().getUuid());
                assertNotEquals(null, parsedResponse.getAddress().getAddedEpoch());
                assertNotEquals(null, parsedResponse.getAddress().getModifiedEpoch());
                assertNotEquals(null, parsedResponse.getAddress().getUuid());
                assertNotEquals(null, parsedResponse.getAddress().getNickname());
                assertNotEquals(null, parsedResponse.getAddress().getStreetAddress1());
                assertNotEquals(null, parsedResponse.getAddress().getStreetAddress2());
                assertNotEquals(null, parsedResponse.getAddress().getCity());
                assertNotEquals(null, parsedResponse.getAddress().getState());
                assertNotEquals(null, parsedResponse.getAddress().getCountry());
                assertNotEquals(null, parsedResponse.getAddress().getPostalCode());
        }

        @Test
        public void Response200Device() throws Exception {
                UserHandleMessage user = UserHandleMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
                Device device = new Device("12345678909876");
                ApiResponse response = api.addDevice(user, device);
                assertEquals(200, response.getStatusCode());
                DeviceResponse parsedResponse = (DeviceResponse) response.getData();
                assertTrue(parsedResponse.getSuccess());
                assertEquals("SUCCESS", parsedResponse.getStatus());
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
        }
        @Test
        public void Response200DeviceWithSessionIdentifier() throws Exception {
                UserHandleMessage user = UserHandleMessage.builder().userHandle(DefaultConfigurations.getUser4Handle())
                        .userPrivateKey(DefaultConfigurations.getUser4PrivateKey()).build();
                Device device = new Device("12345678909876",DefaultConfigurations.sessionIdentifier);
                ApiResponse response = api.addDevice(user, device);
                assertEquals(200, response.getStatusCode());
                DeviceResponse parsedResponse = (DeviceResponse) response.getData();
                assertTrue(parsedResponse.getSuccess());
                assertEquals("SUCCESS", parsedResponse.getStatus());
        }
}
