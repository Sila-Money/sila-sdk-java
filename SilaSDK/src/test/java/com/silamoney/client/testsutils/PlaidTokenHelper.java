package com.silamoney.client.testsutils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class PlaidTokenHelper {
	
	public static String getPlaidToken() throws IOException, InterruptedException {
        HttpRequest finalRequest = PrepareRequest();
        
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        
        JSONObject data;
        
        try {
			data = (JSONObject)new JSONTokener(httpClient.send(finalRequest,
			        BodyHandlers.ofString()).body()).nextValue();
			
			return data.getString("public_token");
		} catch (JSONException e) {
			e.printStackTrace();
		}

        return "";
    }

    private static HttpRequest PrepareRequest() throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://sandbox.plaid.com/link/item/create"));

        request.header("Content-Type", "application/json");

        return request.POST(HttpRequest.BodyPublishers.ofString("{\r\n" + 
										        		"    \"credentials\": {\r\n" + 
										        		"        \"username\": \"user_good\",\r\n" + 
										        		"        \"password\": \"pass_good\"\r\n" + 
										        		"    },\r\n" + 
										        		"    \"initial_products\": [\r\n" + 
										        		"        \"auth\"\r\n" + 
										        		"    ],\r\n" + 
										        		"    \"institution_id\": \"ins_3\",\r\n" + 
										        		"    \"public_key\": \"fa9dd19eb40982275785b09760ab79\"\r\n" + 
										        		"}"))
                .build();
    }

}
