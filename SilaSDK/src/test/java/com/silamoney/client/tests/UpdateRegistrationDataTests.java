package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.AddressMessage;
import com.silamoney.client.domain.AddressResponse;
import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.BusinessEntityMessage;
import com.silamoney.client.domain.BusinessEntityResponse;
import com.silamoney.client.domain.EmailMessage;
import com.silamoney.client.domain.EmailResponse;
import com.silamoney.client.domain.IdentityMessage;
import com.silamoney.client.domain.IdentityResponse;
import com.silamoney.client.domain.IndividualEntityMessage;
import com.silamoney.client.domain.IndividualEntityResponse;
import com.silamoney.client.domain.PhoneMessage;
import com.silamoney.client.domain.PhoneResponse;
import com.silamoney.client.domain.UserHandleMessage;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class UpdateRegistrationDataTests {
        SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                        DefaultConfigurations.privateKey);

        @Test
        public void Response200Email() throws Exception {
                UserHandleMessage user = UserHandleMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
                EmailMessage message = EmailMessage.builder().uuid(DefaultConfigurations.getEmailUuid())
                                .email("some_updated_email@domain.sila").build();
                ApiResponse response = api.updateEmail(user, message);
                assertEquals(200, response.getStatusCode());
                EmailResponse parsedResponse = (EmailResponse) response.getData();
                assertTrue(parsedResponse.getSuccess());
                assertEquals("SUCCESS", parsedResponse.getStatus());
                assertThat(parsedResponse.getMessage(),
                                stringContainsInOrder(Arrays.asList("Successfully updated email")));
                assertThat(parsedResponse.getEmail(), notNullValue());
                assertThat(parsedResponse.getEmail().getAddedEpoch(), notNullValue());
                assertThat(parsedResponse.getEmail().getModifiedEpoch(), notNullValue());
                assertThat(parsedResponse.getEmail().getUuid(), notNullValue());
                assertThat(parsedResponse.getEmail().getEmail(), notNullValue());
        }

        @Test
        public void Response200Phone() throws Exception {
                UserHandleMessage user = UserHandleMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
                PhoneMessage message = PhoneMessage.builder().uuid(DefaultConfigurations.getPhoneUuid())
                                .phone("1234567890").smsOptIn(true).build();
                ApiResponse response = api.updatePhone(user, message);
                assertEquals(200, response.getStatusCode());
                PhoneResponse parsedResponse = (PhoneResponse) response.getData();
                assertTrue(parsedResponse.getSuccess());
                assertEquals("SUCCESS", parsedResponse.getStatus());
                assertThat(parsedResponse.getMessage(),
                                stringContainsInOrder(Arrays.asList("Successfully updated phone")));
                assertThat(parsedResponse.getPhone(), notNullValue());
                assertThat(parsedResponse.getPhone().getAddedEpoch(), notNullValue());
                assertThat(parsedResponse.getPhone().getModifiedEpoch(), notNullValue());
                assertThat(parsedResponse.getPhone().getUuid(), notNullValue());
                assertThat(parsedResponse.getPhone().getPhone(), notNullValue());
        }

        @Test
        public void Response200Identity() throws Exception {
                UserHandleMessage user = UserHandleMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
                IdentityMessage message = IdentityMessage.builder().uuid(DefaultConfigurations.getIdentityUuid())
                                .identityAlias("SSN").identityValue("123452383").build();
                ApiResponse response = api.updateIdentity(user, message);
                assertEquals(200, response.getStatusCode());
                IdentityResponse parsedResponse = (IdentityResponse) response.getData();
                assertTrue(parsedResponse.getSuccess());
                assertEquals("SUCCESS", parsedResponse.getStatus());
                assertThat(parsedResponse.getMessage(),
                                stringContainsInOrder(Arrays.asList("Successfully updated identity")));
                assertThat(parsedResponse.getIdentity(), notNullValue());
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
                AddressMessage message = AddressMessage.builder().uuid(DefaultConfigurations.getAddressUuid())
                                .addressAlias("new address").streetAddress1("324 Songbird Avenue")
                                .streetAddress2("Apt. 132").city("Portland").state("VA").country("US")
                                .postalCode("12345").build();
                ApiResponse response = api.updateAddress(user, message);
                assertEquals(200, response.getStatusCode());
                AddressResponse parsedResponse = (AddressResponse) response.getData();
                assertTrue(parsedResponse.getSuccess());
                assertEquals("SUCCESS", parsedResponse.getStatus());
                assertThat(parsedResponse.getMessage(),
                                stringContainsInOrder(Arrays.asList("Successfully updated address")));
                assertThat(parsedResponse.getAddress(), notNullValue());
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
        public void Response200IndividualEntity() throws Exception {
                UserHandleMessage user = UserHandleMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
                IndividualEntityMessage message = IndividualEntityMessage.builder().firstName("NewFirst")
                                .lastName("NewLast").entityName("NewFirst NewLast").birthdate(LocalDate.of(1994, 1, 8))
                                .build();
                ApiResponse response = api.updateEntity(user, message);
                assertEquals(200, response.getStatusCode());
                IndividualEntityResponse parsedResponse = (IndividualEntityResponse) response.getData();
                assertTrue(parsedResponse.getSuccess());
                assertEquals("SUCCESS", parsedResponse.getStatus());
                assertThat(parsedResponse.getMessage(),
                                stringContainsInOrder(Arrays.asList("Successfully updated entity")));
                assertThat(parsedResponse.getUserHandle(), stringContainsInOrder(
                                Arrays.asList(DefaultConfigurations.getUserHandle().toLowerCase())));
                assertEquals("individual", parsedResponse.getEntityType());
                assertThat(parsedResponse.getEntity(), notNullValue());
                assertThat(parsedResponse.getEntity().getCreatedEpoch(), notNullValue());
                assertThat(parsedResponse.getEntity().getEntityName(), notNullValue());
                assertThat(parsedResponse.getEntity().getBirthdate(), notNullValue());
                assertThat(parsedResponse.getEntity().getFirstName(), notNullValue());
                assertThat(parsedResponse.getEntity().getLastName(), notNullValue());
        }

        @Test
        public void Response200BusinessEntity() throws Exception {
                UserHandleMessage user = UserHandleMessage.builder()
                                .userHandle(DefaultConfigurations.getBusinessHandle())
                                .userPrivateKey(DefaultConfigurations.getBusinessPrivateKey()).build();
                BusinessEntityMessage message = BusinessEntityMessage.builder().entityName("New Company")
                                .birthdate(LocalDate.now())
                                .businessType(DefaultConfigurations.getBusinessTypes().get(0).getName())
                                .naicsCode(DefaultConfigurations.getDefaultNaicCategoryDescription().getCode())
                                .doingBusinessAs("NC").businessWebsite("https://somedomain.go").build();
                ApiResponse response = api.updateEntity(user, message);
                assertEquals(200, response.getStatusCode());
                BusinessEntityResponse parsedResponse = (BusinessEntityResponse) response.getData();
                assertTrue(parsedResponse.getSuccess());
                assertEquals("SUCCESS", parsedResponse.getStatus());
                assertThat(parsedResponse.getMessage(),
                                stringContainsInOrder(Arrays.asList("Successfully updated entity")));
                assertThat(parsedResponse.getUserHandle(), stringContainsInOrder(
                                Arrays.asList(DefaultConfigurations.getBusinessHandle().toLowerCase())));
                assertEquals("business", parsedResponse.getEntityType());
                assertThat(parsedResponse.getEntity(), notNullValue());
                assertThat(parsedResponse.getEntity().getCreatedEpoch(), notNullValue());
                assertThat(parsedResponse.getEntity().getEntityName(), notNullValue());
                assertThat(parsedResponse.getEntity().getBirthdate(), notNullValue());
                assertThat(parsedResponse.getEntity().getBusinessType(), notNullValue());
                assertThat(parsedResponse.getEntity().getNaicsCode(), notNullValue());
                assertThat(parsedResponse.getEntity().getNaicsCategory(), notNullValue());
                assertThat(parsedResponse.getEntity().getNaicsSubcategory(), notNullValue());
                assertThat(parsedResponse.getEntity().getBusinessUuid(), notNullValue());
                assertThat(parsedResponse.getEntity().getDoingBusinessAs(), notNullValue());
                assertThat(parsedResponse.getEntity().getBusinessWebsite(), notNullValue());
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
        }
}
