package com.silamoney.client.tests;

import com.silamoney.client.testsutils.DefaultConfigurations;
import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.MatchType;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;

/**
 * Sets the responses of the mock server.
 *
 * @author Karlo Lorenzana
 */
public class MockServer {

    /**
     * Sets the server for the check handle endpoint.
     */
    public static void MockServer() {
        //<editor-fold defaultstate="collapsed" desc="Check Handle">
        //<editor-fold defaultstate="collapsed" desc="Response 200 success">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/check_handle")
                                .withBody(
                                        JsonBody.json(
                                                "{\"header\":"
                                                + "{"
                                                + "\"auth_handle\":\"handle.silamoney.eth\","
                                                + "\"user_handle\":\"" + DefaultConfigurations.userHandle + "\","
                                                + "\"version\":\"0.2\","
                                                + "\"crypto\":\"ETH\","
                                                + "\"reference\":\"ref\""
                                                + "}"
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{"
                                        + "  \"reference\": \"ref\","
                                        + "  \"message\": \"" + DefaultConfigurations.userHandle + " is available.\","
                                        + "  \"status\": \"SUCCESS\""
                                        + "}")
                );
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Response 200 failure">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/check_handle")
                                .withBody(
                                        JsonBody.json(
                                                "{\"header\":"
                                                + "{"
                                                + "\"auth_handle\":\"handle.silamoney.eth\","
                                                + "\"user_handle\":\"taken.silamoney.eth\","
                                                + "\"version\":\"0.2\","
                                                + "\"crypto\":\"ETH\","
                                                + "\"reference\":\"ref\""
                                                + "}"
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{"
                                        + "  \"reference\": \"ref\","
                                        + "  \"message\": \"taken.silamoney.eth is already taken.\","
                                        + "  \"status\": \"FAILURE\""
                                        + "}")
                );
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Response 400">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/check_handle")
                                .withBody(
                                        JsonBody.json(
                                                "{\"header\":"
                                                + "{"
                                                + "\"auth_handle\":\"handle.silamoney.eth\","
                                                + "\"user_handle\":\"badrequest.silamoney.eth\","
                                                + "\"version\":\"0.2\","
                                                + "\"crypto\":\"ETH\","
                                                + "\"reference\":\"ref\""
                                                + "}"
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(400)
                                .withBody("{\"reference\":\"ref\","
                                        + "\"message\":\"Bad request message.\","
                                        + "\"status\":\"FAILURE\"}")
                );
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Response 401">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/check_handle")
                                .withBody(
                                        JsonBody.json(
                                                "{\"header\":"
                                                + "{"
                                                + "\"auth_handle\":\"handle.silamoney.eth\","
                                                + "\"user_handle\":\"invalidsignature.silamoney.eth\","
                                                + "\"version\":\"0.2\","
                                                + "\"crypto\":\"ETH\","
                                                + "\"reference\":\"ref\""
                                                + "}"
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(401)
                                .withBody("{\"reference\":\"ref\","
                                        + "\"message\":\"Invalid signature message.\","
                                        + "\"status\":\"FAILURE\"}")
                );
        //</editor-fold>
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Register">
        //<editor-fold defaultstate="collapsed" desc="Response 200">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/register")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "\"header\": {"
                                                + "\"reference\": \"ref\","
                                                + "\"user_handle\": \"" + DefaultConfigurations.userHandle + "\","
                                                + "\"auth_handle\": \"handle.silamoney.eth\","
                                                + "\"version\": \"0.2\","
                                                + "\"crypto\": \"ETH\""
                                                + "},"
                                                + "\"message\": \"entity_msg\","
                                                + "\"address\": {"
                                                + "\"address_alias\": \"\","
                                                + "\"street_address_1\": \"123 Main Street\","
                                                + "\"city\": \"New City\","
                                                + "\"state\": \"OR\","
                                                + "\"country\": \"US\","
                                                + "\"postal_code\": \"97204-1234\""
                                                + "},"
                                                + "\"identity\": {"
                                                + "\"identity_alias\": \"SSN\","
                                                + "\"identity_value\": \"123452222\""
                                                + "},"
                                                + "\"contact\": {"
                                                + "\"phone\": \"503-123-4567\","
                                                + "\"contact_alias\": \"\","
                                                + "\"email\": \"example@silamoney.com\""
                                                + "},"
                                                + "\"crypto_entry\": {"
                                                + "\"crypto_alias\": \"\","
                                                + "\"crypto_address\": \"0x1234567890abcdef1234567890abcdef12345678\","
                                                + "\"crypto_code\": \"ETH\""
                                                + "},"
                                                + "\"entity\": {"
                                                + "\"birthdate\": \"1900-01-31\","
                                                + "\"entity_name\": \"Example User\","
                                                + "\"first_name\": \"Example\","
                                                + "\"last_name\": \"User\","
                                                + "\"relationship\": \"user\""
                                                + "}"
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{"
                                        + "  \"reference\":\"ref\","
                                        + "  \"message\":\"" + DefaultConfigurations.userHandle + " was successfully registered \","
                                        + "  \"status\":\"SUCCESS\""
                                        + "}")
                );
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Response 400">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/register")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "\"header\": {"
                                                + "\"reference\": \"ref\","
                                                + "\"user_handle\": \"badrequest.silamoney.eth\","
                                                + "\"auth_handle\": \"handle.silamoney.eth\","
                                                + "\"version\": \"0.2\","
                                                + "\"crypto\": \"ETH\""
                                                + "},"
                                                + "\"message\": \"entity_msg\","
                                                + "\"address\": {"
                                                + "\"address_alias\": \"\","
                                                + "\"street_address_1\": \"123 Main Street\","
                                                + "\"city\": \"New City\","
                                                + "\"state\": \"OR\","
                                                + "\"country\": \"US\","
                                                + "\"postal_code\": \"97204-1234\""
                                                + "},"
                                                + "\"identity\": {"
                                                + "\"identity_alias\": \"SSN\","
                                                + "\"identity_value\": \"123452222\""
                                                + "},"
                                                + "\"contact\": {"
                                                + "\"phone\": \"503-123-4567\","
                                                + "\"contact_alias\": \"\","
                                                + "\"email\": \"example@silamoney.com\""
                                                + "},"
                                                + "\"crypto_entry\": {"
                                                + "\"crypto_alias\": \"\","
                                                + "\"crypto_address\": \"0x1234567890abcdef1234567890abcdef12345678\","
                                                + "\"crypto_code\": \"ETH\""
                                                + "},"
                                                + "\"entity\": {"
                                                + "\"birthdate\": \"1900-01-31\","
                                                + "\"entity_name\": \"Example User\","
                                                + "\"first_name\": \"Example\","
                                                + "\"last_name\": \"User\","
                                                + "\"relationship\": \"user\""
                                                + "}"
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(400)
                                .withBody("{"
                                        + "  \"reference\":\"ref\","
                                        + "  \"message\":\"Bad request message. \","
                                        + "  \"status\":\"FAILURE\""
                                        + "}")
                );
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Response 401">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/register")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "\"header\": {"
                                                + "\"reference\": \"ref\","
                                                + "\"user_handle\": \"invalidsignature.silamoney.eth\","
                                                + "\"auth_handle\": \"handle.silamoney.eth\","
                                                + "\"version\": \"0.2\","
                                                + "\"crypto\": \"ETH\""
                                                + "},"
                                                + "\"message\": \"entity_msg\","
                                                + "\"address\": {"
                                                + "\"address_alias\": \"\","
                                                + "\"street_address_1\": \"123 Main Street\","
                                                + "\"city\": \"New City\","
                                                + "\"state\": \"OR\","
                                                + "\"country\": \"US\","
                                                + "\"postal_code\": \"97204-1234\""
                                                + "},"
                                                + "\"identity\": {"
                                                + "\"identity_alias\": \"SSN\","
                                                + "\"identity_value\": \"123452222\""
                                                + "},"
                                                + "\"contact\": {"
                                                + "\"phone\": \"503-123-4567\","
                                                + "\"contact_alias\": \"\","
                                                + "\"email\": \"example@silamoney.com\""
                                                + "},"
                                                + "\"crypto_entry\": {"
                                                + "\"crypto_alias\": \"\","
                                                + "\"crypto_address\": \"0x1234567890abcdef1234567890abcdef12345678\","
                                                + "\"crypto_code\": \"ETH\""
                                                + "},"
                                                + "\"entity\": {"
                                                + "\"birthdate\": \"1900-01-31\","
                                                + "\"entity_name\": \"Example User\","
                                                + "\"first_name\": \"Example\","
                                                + "\"last_name\": \"User\","
                                                + "\"relationship\": \"user\""
                                                + "}"
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(401)
                                .withBody("{"
                                        + "  \"reference\":\"ref\","
                                        + "  \"message\":\"Invalid signature message. \","
                                        + "  \"status\":\"FAILURE\""
                                        + "}")
                );
        //</editor-fold>
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="RequestKYC">
        //<editor-fold defaultstate="collapsed" desc="Response 200">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/request_kyc")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"auth_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"" + DefaultConfigurations.userHandle + "\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\", "
                                                + "    \"reference\": \"ref\""
                                                + "  }, "
                                                + "  \"message\": \"header_msg\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{"
                                        + "  \"reference\":\"ref\","
                                        + "  \"message\":\"" + DefaultConfigurations.userHandle + " submitted for KYC review\","
                                        + "  \"status\":\"SUCCESS\""
                                        + "}")
                );
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Response 400">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/request_kyc")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"auth_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"badrequest.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\", "
                                                + "    \"reference\": \"ref\""
                                                + "  }, "
                                                + "  \"message\": \"header_msg\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(400)
                                .withBody("{"
                                        + "  \"reference\":\"ref\","
                                        + "  \"message\":\"Bad request message.\","
                                        + "  \"status\":\"FAILURE\""
                                        + "}")
                );
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Response 401">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/request_kyc")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"auth_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"invalidsignature.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\", "
                                                + "    \"reference\": \"ref\""
                                                + "  }, "
                                                + "  \"message\": \"header_msg\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(401)
                                .withBody("{"
                                        + "  \"reference\":\"ref\","
                                        + "  \"message\":\"Invalid signature message.\","
                                        + "  \"status\":\"FAILURE\""
                                        + "}")
                );
        //</editor-fold>
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="CheckKYC">
        //<editor-fold defaultstate="collapsed" desc="Response 200 Success">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/check_kyc")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"auth_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"" + DefaultConfigurations.userHandle + "\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\", "
                                                + "    \"reference\": \"ref\""
                                                + "  }, "
                                                + "  \"message\": \"header_msg\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{"
                                        + "  \"reference\":\"ref\","
                                        + "  \"message\":\"KYC passed for " + DefaultConfigurations.userHandle + "\","
                                        + "  \"status\":\"SUCCESS\""
                                        + "}")
                );
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Response 200 Failure">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/check_kyc")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"auth_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"notpassed.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\", "
                                                + "    \"reference\": \"ref\""
                                                + "  }, "
                                                + "  \"message\": \"header_msg\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{"
                                        + "  \"reference\":\"ref\","
                                        + "  \"message\":\"KYC not passed for notpassed.silamoney.eth\","
                                        + "  \"status\":\"FAILURE\""
                                        + "}")
                );
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Response 400">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/check_kyc")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"auth_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"badrequest.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\", "
                                                + "    \"reference\": \"ref\""
                                                + "  }, "
                                                + "  \"message\": \"header_msg\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(400)
                                .withBody("{"
                                        + "  \"reference\":\"ref\","
                                        + "  \"message\":\"Bad request message.\","
                                        + "  \"status\":\"FAILURE\""
                                        + "}")
                );
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Response 401">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/check_kyc")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"auth_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"invalidsignature.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\", "
                                                + "    \"reference\": \"ref\""
                                                + "  }, "
                                                + "  \"message\": \"header_msg\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(401)
                                .withBody("{"
                                        + "  \"reference\":\"ref\","
                                        + "  \"message\":\"Invalid signature message.\","
                                        + "  \"status\":\"FAILURE\""
                                        + "}")
                );
        //</editor-fold>
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="LinkAccount">
        //<editor-fold defaultstate="collapsed" desc="Response 200 success">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/link_account")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"auth_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"user.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\", "
                                                + "    \"reference\": \"ref\""
                                                + "  }, "
                                                + "  \"message\": \"link_account_msg\","
                                                + "  \"public_token\": \"public-xxx-xxx\","
                                                + "  \"account_name\": \"Custom Account Name\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{"
                                        + "  \"status\": \"SUCCESS\""
                                        + "}")
                );
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Response 200 failure">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/link_account")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"auth_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"failure.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\", "
                                                + "    \"reference\": \"ref\""
                                                + "  }, "
                                                + "  \"message\": \"link_account_msg\","
                                                + "  \"public_token\": \"public-xxx-xxx\","
                                                + "  \"account_name\": \"Custom Account Name\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{"
                                        + "  \"status\": \"FAILURE\""
                                        + "}")
                );
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Response 400">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/link_account")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"auth_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"badrequest.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\", "
                                                + "    \"reference\": \"ref\""
                                                + "  }, "
                                                + "  \"message\": \"link_account_msg\","
                                                + "  \"public_token\": \"public-xxx-xxx\","
                                                + "  \"account_name\": \"Custom Account Name\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(400)
                                .withBody("{"
                                        + "  \"status\": \"FAILURE\""
                                        + "}")
                );
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Response 401">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/link_account")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"auth_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"invalidsignature.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\", "
                                                + "    \"reference\": \"ref\""
                                                + "  }, "
                                                + "  \"message\": \"link_account_msg\","
                                                + "  \"public_token\": \"public-xxx-xxx\","
                                                + "  \"account_name\": \"Custom Account Name\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(401)
                                .withBody("{"
                                        + "  \"status\": \"FAILURE\""
                                        + "}")
                );
        //</editor-fold>
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="GetAccounts">
        //<editor-fold defaultstate="collapsed" desc="Response 200">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/get_accounts")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"auth_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"user.silamoney.eth\", "
                                                + "    \"version\": \"0.2\","
                                                + "    \"crypto\": \"ETH\", "
                                                + "    \"reference\": \"ref\""
                                                + "  }, "
                                                + "  \"message\": \"get_accounts_msg\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("["
                                        + "  {"
                                        + "    \"account_number\": \"*1234\","
                                        + "    \"account_name\": \"default\","
                                        + "    \"account_type\": \"CHECKING\","
                                        + "    \"account_status\": \"active\""
                                        + "  }"
                                        + "]")
                );
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Response 400">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/get_accounts")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"auth_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"badrequest.silamoney.eth\", "
                                                + "    \"version\": \"0.2\","
                                                + "    \"crypto\": \"ETH\", "
                                                + "    \"reference\": \"ref\""
                                                + "  }, "
                                                + "  \"message\": \"get_accounts_msg\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(400)
                                .withBody("Bad request message.")
                );
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Response 401">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/get_accounts")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"auth_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"invalidsignature.silamoney.eth\", "
                                                + "    \"version\": \"0.2\","
                                                + "    \"crypto\": \"ETH\", "
                                                + "    \"reference\": \"ref\""
                                                + "  }, "
                                                + "  \"message\": \"get_accounts_msg\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(400)
                                .withBody("Invalid signature message.")
                );
        //</editor-fold>
        //</editor-fold>
    }
}
