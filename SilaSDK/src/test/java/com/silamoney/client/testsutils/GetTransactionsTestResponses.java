package com.silamoney.client.testsutils;

public class GetTransactionsTestResponses {
    public static String getTransactionsResponse200 = "{" +
            "\"status\":\"SUCCESS\","+
            "\"descriptor\":\"test descriptor\","+
            "\"message\":\"Transaction submitted to the processing queue.\","+
            "\"success\":true,"+
            "\"pagination\": {"+
                "\"returned_count\": 4,"+
                "\"total_count\": 4,"+
                "\"current_page\": 1,"+
                "\"total_pages\": 1"+
            "},"+
            "\"transactions\": ["+
                "{"+
                    "\"user_handle\": \"user\","+
                    "\"reference_id\": \"chosen_id\","+
                    "\"transaction_id\": \"12345678-abcd-1234-abcd-1234567890aa\","+
                    "\"transaction_hash\": \"0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdee\","+
                    "\"transaction_type\": \"transfer\","+
                    "\"provider_tx_id\": null,"+
                    "\"sila_amount\": 100,"+
                    "\"status\": \"success\","+
                    "\"extended_status\": null,"+
                    "\"provider_status\": null,"+
                    "\"created\": \"2020-03-09T17:45:59.709910Z\","+
                    "\"last_update\": \"2020-03-09T17:45:59.709920Z\","+
                    "\"created_epoch\": 1582754399,"+
                    "\"last_update_epoch\": 1582754414,"+
                    "\"descriptor\": \"\","+
                    "\"descriptor_ach\": \"\","+
                    "\"IMAD\": \"\","+
                    "\"OMAD\": \"\","+
                    "\"ach_name\": \"\","+
                    "\"destination_address\": \"0xF08D203754F8960C3655Aa00Bde006DB78fdBf5d\","+
                    "\"destination_handle\": \"user\","+
                    "\"handle_address\": \"0x65a796a4bD3AaF6370791BefFb1A86EAcfdBc3C1\","+
                    "\"source_id\": \"37559d1e-2848-4109-a7d8-a8b250c6e863\","+
                    "\"destination_id\": \"ebd20435-02d4-4fe2-8573-777e712974e8\","+
                    "\"sec_code\": \"PPD\","+
                    "\"timeline\": ["+
                        "{"+
                            "\"date\": \"2020-03-09T17:45:59.716909Z\","+
                            "\"date_epoch\": 1582754399,"+
                            "\"status\": \"queued\""+
                        "},"+
                        "{"+
                            "\"date\": \"2020-03-09T17:45:59.727218Z\","+
                            "\"date_epoch\": 1582754400,"+
                            "\"status\": \"pending\""+
                        "},"+
                        "{"+
                            "\"date\": \"2020-03-09T17:45:59.728691Z\","+
                            "\"date_epoch\": 1582754414,"+
                            "\"status\": \"success\""+
                        "}"+
                    "]"+
                "},"+
                "{"+
                    "\"user_handle\": \"user\","+
                    "\"reference_id\": \"chosen_id\","+
                    "\"transaction_id\": \"12345678-abcd-1234-abcd-1234567890aa\","+
                    "\"transaction_hash\": \"0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdee\","+
                    "\"transaction_type\": \"transfer\","+
                    "\"provider_tx_id\": null,"+
                    "\"sila_amount\": 100,"+
                    "\"status\": \"success\","+
                    "\"extended_status\": null,"+
                    "\"provider_status\": null,"+
                    "\"created\": \"2020-03-09T17:45:59.709910Z\","+
                    "\"last_update\": \"2020-03-09T17:45:59.709920Z\","+
                    "\"created_epoch\": 1582754399,"+
                    "\"last_update_epoch\": 1582754414,"+
                    "\"descriptor\": \"\","+
                    "\"descriptor_ach\": \"\","+
                    "\"error_code\": \"ACH_RETURN\","+
                    "\"error_msg\": \"An ACH return was received for this transaction.\","+
                    "\"error_description\": \"possible additional error message.\","+
                    "\"return_code\": \"R01\","+
                    "\"return_desc\": \"Insufficient funds\","+
                    "\"ach_name\": \"\","+
                    "\"destination_address\": \"0xF08D203754F8960C3655Aa00Bde006DB78fdBf5d\","+
                    "\"destination_handle\": \"user\","+
                    "\"handle_address\": \"0x65a796a4bD3AaF6370791BefFb1A86EAcfdBc3C1\","+
                    "\"source_id\": \"37559d1e-2848-4109-a7d8-a8b250c6e863\","+
                    "\"destination_id\": \"ebd20435-02d4-4fe2-8573-777e712974e8\","+
                    "\"sec_code\": \"PPD\","+
                    "\"timeline\": ["+
                        "{"+
                            "\"date\": \"2020-03-09T17:45:59.716909Z\","+
                            "\"date_epoch\": 1582754399,"+
                            "\"status\": \"queued\""+
                        "},"+
                        "{"+
                            "\"date\": \"2020-03-09T17:45:59.727218Z\","+
                            "\"date_epoch\": 1582754400,"+
                            "\"status\": \"pending\""+
                        "},"+
                        "{"+
                            "\"date\": \"2020-03-09T17:45:59.728691Z\","+
                            "\"date_epoch\": 1582754414,"+
                            "\"status\": \"success\""+
                        "}"+
                    "]"+
                "}"+
            "]"+
        "}";

    public static String getTransactionsSourceIdDestinationId = "{" +
            "\"status\":\"SUCCESS\"," +
            "\"descriptor\":\"test descriptor\"," +
            "\"message\":\"Transaction submitted to the processing queue.\"," +
            "\"success\":true," +
            "\"pagination\": {" +
                "\"returned_count\": 4," +
                "\"total_count\": 4," +
                "\"current_page\": 1," +
                "\"total_pages\": 1" +
            "}," +
            "\"transactions\": [" +
                "{" +
                    "\"user_handle\": \"user\"," +
                    "\"reference_id\": \"<your unique id>\"," +
                    "\"transaction_id\": \"12345678-abcd-1234-abcd-1234567890aa\"," +
                    "\"transaction_hash\": \"0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdee\"," +
                    "\"transaction_type\": \"transfer\"," +
                    "\"sila_ledger_type\":\"Internal\"," +
                    "\"provider_tx_id\": null," +
                    "\"sila_amount\": 100," +
                    "\"status\": \"success\"," +
                    "\"extended_status\": null," +
                    "\"provider_status\": null," +
                    "\"created\": \"2020-03-09T17:45:59.709910Z\"," +
                    "\"last_update\": \"2020-03-09T17:45:59.709920Z\"," +
                    "\"created_epoch\": 1582754399," +
                    "\"last_update_epoch\": 1582754414," +
                    "\"descriptor\": \"\"," +
                    "\"descriptor_ach\": \"\"," +
                    "\"IMAD\": \"\"," +
                    "\"OMAD\": \"\"," +
                    "\"ach_name\": \"\"," +
                    "\"destination_address\": \"0xF08D203754F8960C3655Aa00Bde006DB78fdBf5d\"," +
                    "\"destination_handle\": \"user\"," +
                    "\"handle_address\": \"0x65a796a4bD3AaF6370791BefFb1A86EAcfdBc3C1\"," +
                    "\"source_id\": \"37559d1e-2848-4109-a7d8-a8b250c6e863\"," +
                    "\"destination_id\": \"ebd20435-02d4-4fe2-8573-777e712974e8\"," +
                    "\"sec_code\": \"PPD\"," +
                    "\"timeline\": [" +
                        "{" +
                            "\"date\": \"2020-03-09T17:45:59.716909Z\"," +
                            "\"date_epoch\": 1582754399," +
                            "\"status\": \"queued\"" +
                        "}," +
                        "{" +
                            "\"date\": \"2020-03-09T17:45:59.727218Z\"," +
                            "\"date_epoch\": 1582754400," +
                            "\"status\": \"pending\"" +
                        "}," +
                        "{" +
                            "\"date\": \"2020-03-09T17:45:59.728691Z\"," +
                            "\"date_epoch\": 1582754414," +
                            "\"status\": \"success\"" +
                        "}" +
                    "]" +
                "}" +
            "]" +
        "}";

    public static String getTransactionsInstantSettlement = "{" +
            "\"status\":\"SUCCESS\"," +
            "\"descriptor\":\"test descriptor\"," +
            "\"message\":\"Transaction submitted to the processing queue.\"," +
            "\"success\":true," +
            "\"pagination\": {" +
                "\"returned_count\": 4," +
                "\"total_count\": 4," +
                "\"current_page\": 1," +
                "\"total_pages\": 1" +
            "}," +
            "\"transactions\": [" +
                "{" +
                    "\"user_handle\": \"user\"," +
                    "\"reference_id\": \"chosen_id\"," +
                    "\"transaction_id\": \"12345678-abcd-1234-abcd-1234567890aa\"," +
                    "\"transaction_hash\": \"0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdee\"," +
                    "\"transaction_type\": \"issue\"," +
                    "\"processing_type\": \"INSTANT_SETTLEMENT\"," +
                    "\"provider_tx_id\": null," +
                    "\"sila_amount\": 100," +
                    "\"status\": \"success\"," +
                    "\"extended_status\": null," +
                    "\"provider_status\": null," +
                    "\"created\": \"2020-03-09T17:45:59.709910Z\"," +
                    "\"last_update\": \"2020-03-09T17:45:59.709920Z\"," +
                    "\"created_epoch\": 1582754399," +
                    "\"last_update_epoch\": 1582754414," +
                    "\"descriptor\": \"\"," +
                    "\"descriptor_ach\": \"\"," +
                    "\"IMAD\": \"\"," +
                    "\"OMAD\": \"\"," +
                    "\"ach_name\": \"\"," +
                    "\"destination_address\": \"0xF08D203754F8960C3655Aa00Bde006DB78fdBf5d\"," +
                    "\"destination_handle\": \"user\"," +
                    "\"handle_address\": \"0x65a796a4bD3AaF6370791BefFb1A86EAcfdBc3C1\"," +
                    "\"source_id\": \"37559d1e-2848-4109-a7d8-a8b250c6e863\"," +
                    "\"destination_id\": \"ebd20435-02d4-4fe2-8573-777e712974e8\"," +
                    "\"sec_code\": \"PPD\"," +
                    "\"timeline\": [" +
                        "{" +
                            "\"date\": \"2020-03-09T17:45:59.716909Z\"," +
                            "\"date_epoch\": 1582754399," +
                            "\"status\": \"queued\"" +
                        "}," +
                        "{" +
                            "\"date\": \"2020-03-09T17:45:59.727218Z\"," +
                            "\"date_epoch\": 1582754400," +
                            "\"status\": \"pending\"" +
                        "}," +
                        "{" +
                            "\"date\": \"2020-03-09T17:45:59.728691Z\"," +
                            "\"date_epoch\": 1582754414," +
                            "\"status\": \"success\"" +
                        "}" +
                    "]" +
                "}" +
            "]" +
        "}";

    public static String getTransactionsPaymentMethodId = "{" +
            "\"status\":\"SUCCESS\"," +
            "\"descriptor\":\"test descriptor\"," +
            "\"message\":\"Transaction submitted to the processing queue.\"," +
            "\"success\":true," +
            "\"pagination\": {" +
                "\"returned_count\": 4," +
                "\"total_count\": 4," +
                "\"current_page\": 1," +
                "\"total_pages\": 1" +
            "}," +
            "\"transactions\": [" +
                "{" +
                "\"user_handle\": \"user\"," +
                "\"reference_id\": \"<your unique id>\"," +
                "\"transaction_id\": \"12345678-abcd-1234-abcd-1234567890aa\"," +
                "\"transaction_hash\": \"0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdee\"," +
                "\"transaction_type\": \"transfer\"," +
                "\"provider_tx_id\": null," +
                "\"sila_amount\": 100," +
                "\"status\": \"success\"," +
                "\"extended_status\": null," +
                "\"provider_status\": null," +
                "\"created\": \"2020-03-09T17:45:59.709910Z\"," +
                "\"last_update\": \"2020-03-09T17:45:59.709920Z\"," +
                "\"created_epoch\": 1582754399," +
                "\"last_update_epoch\": 1582754414," +
                "\"descriptor\": \"\"," +
                "\"descriptor_ach\": \"\"," +
                "\"IMAD\": \"\"," +
                "\"OMAD\": \"\"," +
                "\"ach_name\": \"\"," +
                "\"destination_address\": \"0xF08D203754F8960C3655Aa00Bde006DB78fdBf5d\"," +
                "\"destination_handle\": \"user\"," +
                "\"handle_address\": \"0x65a796a4bD3AaF6370791BefFb1A86EAcfdBc3C1\"," +
                "\"source_id\": \"37559d1e-2848-4109-a7d8-a8b250c6e863\"," +
                "\"destination_id\": \"ebd20435-02d4-4fe2-8573-777e712974e8\"," +
                "\"sec_code\": \"PPD\"," +
                "\"timeline\": [" +
                    "{" +
                        "\"date\": \"2020-03-09T17:45:59.716909Z\"," +
                        "\"date_epoch\": 1582754399," +
                        "\"status\": \"queued\"" +
                    "}," +
                    "{" +
                        "\"date\": \"2020-03-09T17:45:59.727218Z\"," +
                        "\"date_epoch\": 1582754400," +
                        "\"status\": \"pending\"" +
                    "}," +
                    "{" +
                        "\"date\": \"2020-03-09T17:45:59.728691Z\"," +
                        "\"date_epoch\": 1582754414," +
                        "\"status\": \"success\"" +
                    "}" +
                "]" +
                "}" +
            "]" +
        "}";

    public static String getTransactionsWithoutUserHandle = "{" +
            "\"status\":\"SUCCESS\"," +
            "\"descriptor\":\"test descriptor\"," +
            "\"message\":\"Transaction submitted to the processing queue.\"," +
            "\"success\":true," +
            "\"pagination\": {" +
                "\"returned_count\": 4," +
                "\"total_count\": 4," +
                "\"current_page\": 1," +
                "\"total_pages\": 1" +
            "}," +
            "\"transactions\": [" +
                "{" +
                    "\"user_handle\": \"user\"," +
                    "\"reference_id\": \"<your unique id>\"," +
                    "\"transaction_id\": \"12345678-abcd-1234-abcd-1234567890aa\"," +
                    "\"transaction_hash\": \"0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdee\"," +
                    "\"transaction_type\": \"transfer\"," +
                    "\"provider_tx_id\": null," +
                    "\"sila_amount\": 100," +
                    "\"status\": \"success\"," +
                    "\"extended_status\": null," +
                    "\"provider_status\": null," +
                    "\"created\": \"2020-03-09T17:45:59.709910Z\"," +
                    "\"last_update\": \"2020-03-09T17:45:59.709920Z\"," +
                    "\"created_epoch\": 1582754399," +
                    "\"last_update_epoch\": 1582754414," +
                    "\"descriptor\": \"\"," +
                    "\"descriptor_ach\": \"\"," +
                    "\"IMAD\": \"\"," +
                    "\"OMAD\": \"\"," +
                    "\"ach_name\": \"\"," +
                    "\"destination_address\": \"0xF08D203754F8960C3655Aa00Bde006DB78fdBf5d\"," +
                    "\"destination_handle\": \"user\"," +
                    "\"handle_address\": \"0x65a796a4bD3AaF6370791BefFb1A86EAcfdBc3C1\"," +
                    "\"source_id\": \"37559d1e-2848-4109-a7d8-a8b250c6e863\"," +
                    "\"destination_id\": \"ebd20435-02d4-4fe2-8573-777e712974e8\"," +
                    "\"sec_code\": \"PPD\"," +
                    "\"timeline\": [" +
                        "{" +
                            "\"date\": \"2020-03-09T17:45:59.716909Z\"," +
                            "\"date_epoch\": 1582754399," +
                            "\"status\": \"queued\"" +
                        "}," +
                        "{" +
                            "\"date\": \"2020-03-09T17:45:59.727218Z\"," +
                            "\"date_epoch\": 1582754400," +
                            "\"status\": \"pending\"" +
                        "}," +
                        "{" +
                            "\"date\": \"2020-03-09T17:45:59.728691Z\"," +
                            "\"date_epoch\": 1582754414," +
                            "\"status\": \"success\"" +
                        "}" +
                    "]" +
                "}" +
            "]" +
        "}";
}

