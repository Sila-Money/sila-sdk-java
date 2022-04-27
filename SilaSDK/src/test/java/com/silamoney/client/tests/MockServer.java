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
@SuppressWarnings("all")
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
                                                + "\"app_handle\":\"handle.silamoney.eth\","
                                                + "\"user_handle\":\"" + DefaultConfigurations.getUserHandle() + "\","
                                                + "\"version\":\"0.2\","
                                                + "\"crypto\":\"ETH\""
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
                                        + "  \"message\": \"" + DefaultConfigurations.getUserHandle() + " is available.\","
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
                                                + "\"app_handle\":\"handle.silamoney.eth\","
                                                + "\"user_handle\":\"taken.silamoney.eth\","
                                                + "\"version\":\"0.2\","
                                                + "\"crypto\":\"ETH\""
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
                                                + "\"app_handle\":\"handle.silamoney.eth\","
                                                + "\"user_handle\":\"badrequest.silamoney.eth\","
                                                + "\"version\":\"0.2\","
                                                + "\"crypto\":\"ETH\""
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
                                                + "\"app_handle\":\"handle.silamoney.eth\","
                                                + "\"user_handle\":\"invalidsignature.silamoney.eth\","
                                                + "\"version\":\"0.2\","
                                                + "\"crypto\":\"ETH\""
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
                                                + "\"user_handle\": \"" + DefaultConfigurations.getUserHandle() + "\","
                                                + "\"app_handle\": \"handle.silamoney.eth\","
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
                                                + "\"identity_value\": \"123452383\""
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
                                        + "  \"message\":\"" + DefaultConfigurations.getUserHandle() + " was successfully registered \","
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
                                                + "\"user_handle\": \"badrequest.silamoney.eth\","
                                                + "\"app_handle\": \"handle.silamoney.eth\","
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
                                                + "\"identity_value\": \"123452383\""
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
                                                + "\"user_handle\": \"invalidsignature.silamoney.eth\","
                                                + "\"app_handle\": \"handle.silamoney.eth\","
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
                                                + "\"identity_value\": \"123452383\""
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
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"" + DefaultConfigurations.getUserHandle() + "\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
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
                                        + "  \"message\":\"" + DefaultConfigurations.getUserHandle() + " submitted for KYC review\","
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
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"badrequest.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
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
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"invalidsignature.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
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
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"" + DefaultConfigurations.getUserHandle() + "\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
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
                                        + "  \"message\":\"KYC passed for " + DefaultConfigurations.getUserHandle() + "\","
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
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"notpassed.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
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
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"badrequest.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
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
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"invalidsignature.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
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
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"" + DefaultConfigurations.getUserHandle() + "\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
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
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"failure.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
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
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"badrequest.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
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
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"invalidsignature.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
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
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"" + DefaultConfigurations.getUserHandle() + "\", "
                                                + "    \"version\": \"0.2\","
                                                + "    \"crypto\": \"ETH\""
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
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"badrequest.silamoney.eth\", "
                                                + "    \"version\": \"0.2\","
                                                + "    \"crypto\": \"ETH\""
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
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"invalidsignature.silamoney.eth\", "
                                                + "    \"version\": \"0.2\","
                                                + "    \"crypto\": \"ETH\""
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

        //<editor-fold defaultstate="collapsed" desc="IssueSila">
        //<editor-fold defaultstate="collapsed" desc="Response 200 Success">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/issue_sila")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"" + DefaultConfigurations.getUserHandle() + "\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
                                                + "  }, "
                                                + "  \"message\": \"issue_msg\","
                                                + "  \"amount\": 1000,"
                                                + "  \"account_name\": \"default\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{"
                                        + "  \"reference\": \"ref\","
                                        + "  \"message\": \"ref submitted to ACH queue\","
                                        + "  \"status\": \"SUCCESS\""
                                        + "}")
                );
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Response 200 Failure">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/issue_sila")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"failure.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
                                                + "  }, "
                                                + "  \"message\": \"issue_msg\","
                                                + "  \"amount\": 1000,"
                                                + "  \"account_name\": \"default\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{"
                                        + "  \"reference\": \"ref\","
                                        + "  \"message\": \"ref not submitted to ACH queue\","
                                        + "  \"status\": \"FAILURE\""
                                        + "}")
                );
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Response 400">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/issue_sila")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"badrequest.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
                                                + "  }, "
                                                + "  \"message\": \"issue_msg\","
                                                + "  \"amount\": 1000,"
                                                + "  \"account_name\": \"default\""
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
                                .withPath("/issue_sila")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"invalidsignature.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
                                                + "  }, "
                                                + "  \"message\": \"issue_msg\","
                                                + "  \"amount\": 1000,"
                                                + "  \"account_name\": \"default\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(401)
                                .withBody("Invalid signature message.")
                );
        //</editor-fold>
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="TransferSila">
        //<editor-fold defaultstate="collapsed" desc="Response 200 Success">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/transfer_sila")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"" + DefaultConfigurations.getUserHandle() + "\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
                                                + "  }, "
                                                + "  \"message\": \"transfer_msg\","
                                                + "  \"amount\": 13,"
                                                + "  \"destination\": \"user2.silamoney.eth\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{"
                                        + "  \"reference\": \"ref\","
                                        + "  \"message\": \"ref submitted to ETH queue\","
                                        + "  \"status\": \"SUCCESS\""
                                        + "}")
                );
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Response 200 Failure">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/transfer_sila")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"failure.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
                                                + "  }, "
                                                + "  \"message\": \"transfer_msg\","
                                                + "  \"amount\": 13,"
                                                + "  \"destination\": \"user2.silamoney.eth\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{"
                                        + "  \"reference\": \"ref\","
                                        + "  \"message\": \"ref not submitted to ETH queue\","
                                        + "  \"status\": \"FAILURE\""
                                        + "}")
                );
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Response 400">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/transfer_sila")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"badrequest.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
                                                + "  }, "
                                                + "  \"message\": \"transfer_msg\","
                                                + "  \"amount\": 13,"
                                                + "  \"destination\": \"user2.silamoney.eth\""
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
                                .withPath("/transfer_sila")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"invalidsignature.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
                                                + "  }, "
                                                + "  \"message\": \"transfer_msg\","
                                                + "  \"amount\": 13,"
                                                + "  \"destination\": \"user2.silamoney.eth\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(401)
                                .withBody("Invalid signature message.")
                );
        //</editor-fold>
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="RedeemSila">
        //<editor-fold defaultstate="collapsed" desc="Response 200 Success">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/redeem_sila")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"" + DefaultConfigurations.getUserHandle() + "\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
                                                + "  }, "
                                                + "  \"message\": \"redeem_msg\","
                                                + "  \"amount\": 1000,"
                                                + "  \"account_name\": \"default\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{"
                                        + "  \"reference\": \"ref\","
                                        + "  \"message\": \"ref submitted to ETH queue\","
                                        + "  \"status\": \"SUCCESS\""
                                        + "}")
                );
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Response 200 Failure">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/redeem_sila")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"failure.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
                                                + "  }, "
                                                + "  \"message\": \"redeem_msg\","
                                                + "  \"amount\": 1000,"
                                                + "  \"account_name\": \"default\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{"
                                        + "  \"reference\": \"ref\","
                                        + "  \"message\": \"ref not submitted to ETH queue\","
                                        + "  \"status\": \"FAILURE\""
                                        + "}")
                );
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Response 400">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/redeem_sila")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"badrequest.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
                                                + "  }, "
                                                + "  \"message\": \"redeem_msg\","
                                                + "  \"amount\": 1000,"
                                                + "  \"account_name\": \"default\""
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
                                .withPath("/redeem_sila")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"invalidsignature.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
                                                + "  }, "
                                                + "  \"message\": \"redeem_msg\","
                                                + "  \"amount\": 1000,"
                                                + "  \"account_name\": \"default\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(401)
                                .withBody("Invalid signature message.")
                );
        //</editor-fold>
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="GetTransactions">
        //<editor-fold defaultstate="collapsed" desc="Response 200">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/get_transactions")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"user.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
                                                + "  }, "
                                                + "  \"message\": \"get_transactions_msg\","
                                                + "  \"search_filters\": {"
                                                + "    \"transaction_id\": \"some UUID string assigned by Sila\","
                                                + "    \"reference_id\": \"the reference string sent in the header object when transaction request was made\","
                                                + "    \"show_timelines\": true,"
                                                + "    \"sort_ascending\": false,"
                                                + "    \"max_sila_amount\": 1300,"
                                                + "    \"min_sila_amount\": 1000,"
                                                + "    \"statuses\": [\"pending\", \"successful\", \"failed\", \"complete\"],"
                                                + "    \"start_epoch\": 1234567860,"
                                                + "    \"end_epoch\": 1234567891,"
                                                + "    \"page\": 1,"
                                                + "    \"per_page\": 20,"
                                                + "    \"transaction_types\": [\"issue\", \"redeem\", \"transfer\"]"
                                                + "  }"
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{"
                                        + "  \"success\": true,"
                                        + "  \"page\": 1,"
                                        + "  \"returned_count\": 1,"
                                        + "  \"total_count\": 1,"
                                        + "  \"transactions\": ["
                                        + "    {"
                                        + "      \"user_handle\": \"user.silamoney.eth\","
                                        + "      \"reference_id\": \"ref\","
                                        + "      \"transaction_id\": \"12345678-abcd-1234-abcd-1234567890ab\","
                                        + "      \"transaction_hash\": \"0x1234567890abcdef1234567890abcdef\","
                                        + "      \"transaction_type\": \"issue\","
                                        + "      \"sila_amount\": 1000,"
                                        + "      \"bank_account_name\": \"default\","
                                        + "      \"handle_address\": \"0x65a796a4bD3AaF6370791BefFb1A86EAcfdBc3C1\","
                                        + "      \"status\": \"success\","
                                        + "      \"usd_status\": \"success\","
                                        + "      \"token_status\": \"success\","
                                        + "      \"created\": \"2019-04-03T00:00:00.000Z\","
                                        + "      \"last_update\": \"2019-04-03T00:00:00.003Z\","
                                        + "      \"created_epoch\": 1234567890,"
                                        + "      \"last_update_epoch\": 1234567899,"
                                        + "      \"timeline\": ["
                                        + "        {"
                                        + "          \"date\": \"2019-04-03T00:00:00.000Z\","
                                        + "          \"date_epoch\": 1234567890,"
                                        + "          \"status\": \"queued\","
                                        + "          \"usd_status\": \"not started\","
                                        + "          \"token_status\": \"not started\""
                                        + "        },"
                                        + "        {"
                                        + "          \"date\": \"2019-04-03T00:00:00.001Z\","
                                        + "          \"date_epoch\": 1234567890,"
                                        + "          \"status\": \"pending\","
                                        + "          \"usd_status\": \"pending\","
                                        + "          \"token_status\": \"not started\""
                                        + "        },"
                                        + "        {"
                                        + "          \"date\": \"2019-04-03T00:00:00.002Z\","
                                        + "          \"date_epoch\": 1234567890,"
                                        + "          \"status\": \"pending\","
                                        + "          \"usd_status\": \"success\","
                                        + "          \"token_status\": \"pending\""
                                        + "        },"
                                        + "        {"
                                        + "          \"date\": \"2019-04-03T00:00:00.003Z\","
                                        + "          \"date_epoch\": 1234567899,"
                                        + "          \"status\": \"success\","
                                        + "          \"usd_status\": \"success\","
                                        + "          \"token_status\": \"success\""
                                        + "        }"
                                        + "      ]"
                                        + "    }"
                                        + "  ]"
                                        + "}")
                );
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Response 400">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/get_transactions")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"badrequest.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
                                                + "  }, "
                                                + "  \"message\": \"get_transactions_msg\","
                                                + "  \"search_filters\": {"
                                                + "    \"transaction_id\": \"some UUID string assigned by Sila\","
                                                + "    \"reference_id\": \"the reference string sent in the header object when transaction request was made\","
                                                + "    \"show_timelines\": true,"
                                                + "    \"sort_ascending\": false,"
                                                + "    \"max_sila_amount\": 1300,"
                                                + "    \"min_sila_amount\": 1000,"
                                                + "    \"statuses\": [\"pending\", \"successful\", \"failed\", \"complete\"],"
                                                + "    \"start_epoch\": 1234567860,"
                                                + "    \"end_epoch\": 1234567891,"
                                                + "    \"page\": 1,"
                                                + "    \"per_page\": 20,"
                                                + "    \"transaction_types\": [\"issue\", \"redeem\", \"transfer\"]"
                                                + "  }"
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

        //<editor-fold defaultstate="collapsed" desc="Response 403">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/get_transactions")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"forbidden.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
                                                + "  }, "
                                                + "  \"message\": \"get_transactions_msg\","
                                                + "  \"search_filters\": {"
                                                + "    \"transaction_id\": \"some UUID string assigned by Sila\","
                                                + "    \"reference_id\": \"the reference string sent in the header object when transaction request was made\","
                                                + "    \"show_timelines\": true,"
                                                + "    \"sort_ascending\": false,"
                                                + "    \"max_sila_amount\": 1300,"
                                                + "    \"min_sila_amount\": 1000,"
                                                + "    \"statuses\": [\"pending\", \"successful\", \"failed\", \"complete\"],"
                                                + "    \"start_epoch\": 1234567860,"
                                                + "    \"end_epoch\": 1234567891,"
                                                + "    \"page\": 1,"
                                                + "    \"per_page\": 20,"
                                                + "    \"transaction_types\": [\"issue\", \"redeem\", \"transfer\"]"
                                                + "  }"
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(403)
                                .withBody("Forbidden message.")
                );
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Response 500">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/get_transactions")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"header\": {"
                                                + "    \"app_handle\": \"handle.silamoney.eth\", "
                                                + "    \"user_handle\":\"serverside.silamoney.eth\", "
                                                + "    \"version\": \"0.2\", "
                                                + "    \"crypto\": \"ETH\""
                                                + "  }, "
                                                + "  \"message\": \"get_transactions_msg\","
                                                + "  \"search_filters\": {"
                                                + "    \"transaction_id\": \"some UUID string assigned by Sila\","
                                                + "    \"reference_id\": \"the reference string sent in the header object when transaction request was made\","
                                                + "    \"show_timelines\": true,"
                                                + "    \"sort_ascending\": false,"
                                                + "    \"max_sila_amount\": 1300,"
                                                + "    \"min_sila_amount\": 1000,"
                                                + "    \"statuses\": [\"pending\", \"successful\", \"failed\", \"complete\"],"
                                                + "    \"start_epoch\": 1234567860,"
                                                + "    \"end_epoch\": 1234567891,"
                                                + "    \"page\": 1,"
                                                + "    \"per_page\": 20,"
                                                + "    \"transaction_types\": [\"issue\", \"redeem\", \"transfer\"]"
                                                + "  }"
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(500)
                                .withBody("Server side error message.")
                );
        //</editor-fold>
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="SilaBalance">
        //<editor-fold defaultstate="collapsed" desc="Response">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/silaBalance")
                                .withBody(
                                        JsonBody.json(
                                                "{"
                                                + "  \"address\": \"0xabc123abc123abc123\""
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("1000")
                );
        //</editor-fold>
        //</editor-fold>
    }
}
