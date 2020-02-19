package com.silamoney.client.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

/**
 *
 * @author Karlo Lorenzana
 */
public class ApiClient {

    private String BasePath;

    /**
     * Gets the api base path.
     * @return
     */
    public String getBasePath() {
        return BasePath;
    }

    /**
     * Sets the api base path.
     * @param BasePath
     */
    public void setBasePath(String BasePath) {
        this.BasePath = BasePath;
    }

    /**
     * HTTP client used to make the API calls.
     */
    private HttpClient httpClient;

    /**
     * API client constructor.
     */
    public ApiClient() {
        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }

    /**
     * Makes the call to the sila API.
     *
     * @param path
     * @param headers
     * @param body
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public HttpResponse CallApi(String path, Map<String, String> headers,
            String body) throws IOException, InterruptedException {
        HttpRequest finalRequest = PrepareRequest(path, headers, body);

        return httpClient.send(finalRequest,
                BodyHandlers.ofString());
    }

    private HttpRequest PrepareRequest(String path, Map<String, String> headers,
            String body) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(BasePath + path));

        headers.entrySet().forEach((entry) -> {
            request.header(entry.getKey(), entry.getValue());
        });

        return request.POST(HttpRequest.BodyPublishers
                .ofString(body))
                .build();
    }
}
