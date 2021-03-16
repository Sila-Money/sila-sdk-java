package com.silamoney.client.api;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/**
 *
 * @author Karlo Lorenzana
 */
public class ApiClient {

    private static final Log log = LogFactory.getFactory().getInstance(ApiClient.class);

    private String basePath;

    /**
     * Gets the api base path.
     * 
     * @return basePath
     */
    public String getBasePath() {
        return basePath;
    }

    /**
     * Sets the api base path.
     * 
     * @param basePath
     */
    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    /**
     * HTTP client used to make the API calls.
     */
    private HttpClient httpClient;

    /**
     * API client constructor.
     */
    public ApiClient() {
        httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
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
    @SuppressWarnings("all")
    public HttpResponse callApi(String path, Map<String, String> headers, String body)
            throws IOException, InterruptedException {
        try {
            logRequest(path, headers, body);
            HttpRequest finalRequest = prepareRequest(path, headers, body);
            return httpClient.send(finalRequest, BodyHandlers.ofString());
        } catch (Exception ex) {
            log.error(Map.of("message", "Error calling api", "error", ex, "path", path, "body", body));
            throw new RuntimeException(ex);
        }
    }

    /**
     * 
     * @param path
     * @param headers
     * @param body
     * @param inputStream
     * @param fileName
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws InterruptedException
     */
    @SuppressWarnings("all")
    public HttpResponse callApi(String path, Map<String, String> headers, String body, InputStream inputStream,
           String fileName, String contentType) throws FileNotFoundException, IOException, InterruptedException {
        String fullPath = basePath + path;
        logRequest(fullPath, headers, body);
        try {
            var request = HttpRequest.newBuilder().uri(URI.create(fullPath));
            headers.entrySet().forEach(entry -> request.header(entry.getKey(), entry.getValue()));
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("data", body, ContentType.TEXT_PLAIN);
            builder.addBinaryBody("file", inputStream, ContentType.create(contentType), fileName);
            HttpEntity multipart = builder.build();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            multipart.writeTo(outStream);
            request.POST(HttpRequest.BodyPublishers.ofByteArray(outStream.toByteArray()));
            outStream.close();
            request.header("Content-Type", multipart.getContentType().getValue());
            return httpClient.send(request.build(), BodyHandlers.ofString());
        } catch (Exception ex) {
            log.error(Map.of("message", "Error calling api", "error", ex, "http_request_uri", fullPath, "body", body));
            throw new RuntimeException(ex);
        }

    }

    private HttpRequest prepareRequest(String path, Map<String, String> headers, String body) {
        String fullPath = basePath + path;
        logRequest(fullPath, headers, body);
        var request = HttpRequest.newBuilder().uri(URI.create(fullPath));
        headers.entrySet().forEach(entry -> request.header(entry.getKey(), entry.getValue()));

        return request.POST(HttpRequest.BodyPublishers.ofString(body)).build();
    }

    private void logRequest(String path, Map<String, String> headers, String body) {
        log.info(Map.of("body", body!=null ? body: "",
            "http_request_uri", path,
            "headers", headers != null ? headers : Collections.emptyMap()));
    }

}
