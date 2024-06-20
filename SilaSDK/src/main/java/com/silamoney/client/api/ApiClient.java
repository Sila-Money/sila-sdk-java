package com.silamoney.client.api;

import com.silamoney.client.domain.UploadDocument;
import com.silamoney.client.util.DefaultLogger;
import com.silamoney.client.util.Logger;
import com.silamoney.client.util.ResponseUtil;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import java.util.logging.Level;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/**
 *
 * @author Karlo Lorenzana
 */
public class ApiClient {

    private String basePath;

    private static final String PRODUCT = "SilaSDK-java";
    private static final String VERSION = "1.0.1";

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
     * Logger logger to trace API calls
     */
    private Logger logger;

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * API client constructor.
     */


    public ApiClient() {
        httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        logger = new DefaultLogger(ApiClient.class);
    }

    /**
     * Request timeout.
     *
     */
    private int timeout;
    /**
     * Sets the request timeout.
     *
     * @param timeout
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
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
            headers.put("User-Agent", PRODUCT + '/' + VERSION);
            logRequest(path, headers, body);
            HttpRequest finalRequest = prepareRequest(path, headers, body);
            return httpClient.send(finalRequest, BodyHandlers.ofString());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, Map.of("message", "Error calling api", "error", ex, "path", path, "body", body));
            throw new RuntimeException(ex);
        }
    }

    /**
     *
     * @param path
     * @param headers
     * @param body
     * @param filePath
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws InterruptedException
     */
    @SuppressWarnings("all")
    public HttpResponse callApi(String path, Map<String, String> headers, String body, String filePath,
                                String contentType) throws FileNotFoundException, IOException, InterruptedException {
        final File file = new File(filePath);
        return callApi(path, headers, body, new FileInputStream(file), contentType, file.getName());
    }

    /**
     *
     * @param path
     * @param headers
     * @param body
     * @param filePath
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws InterruptedException
     */
    @SuppressWarnings("all")
    public HttpResponse callApi(String path, Map<String, String> headers, String body, InputStream binaryStream,
                                String contentType, String name) throws FileNotFoundException, IOException, InterruptedException {
        String fullPath = basePath + path;
        logRequest(fullPath, headers, body);
        try {
            headers.put("User-Agent", PRODUCT + '/' + VERSION);
            var request = HttpRequest.newBuilder().uri(URI.create(basePath + path));
            headers.entrySet().forEach(entry -> request.header(entry.getKey(), entry.getValue()));
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("data", body, ContentType.TEXT_PLAIN);
            builder.addBinaryBody("file", binaryStream, ContentType.create(contentType), name);
            HttpEntity multipart = builder.build();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            multipart.writeTo(outStream);
            request.POST(HttpRequest.BodyPublishers.ofByteArray(outStream.toByteArray()));
            outStream.close();
            request.header("Content-Type", multipart.getContentType().getValue());
            addTimeout(request);
            return httpClient.send(request.build(), BodyHandlers.ofString());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, Map.of("message", "Error calling api", "error", ex, "http_request_uri", fullPath, "body", body));
            throw new RuntimeException(ex);
        }
    }

    public HttpResponse callApi(String path, Map<String, String> headers, String body, ArrayList<UploadDocument> uploadDocumentList) throws FileNotFoundException, IOException, InterruptedException {
        String fullPath = basePath + path;
        logRequest(fullPath, headers, body);
        try {
            headers.put("User-Agent", PRODUCT + '/' + VERSION);
            var request = HttpRequest.newBuilder().uri(URI.create(basePath + path));
            headers.entrySet().forEach(entry -> request.header(entry.getKey(), entry.getValue()));
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("data", body, ContentType.TEXT_PLAIN);
            if (uploadDocumentList != null && uploadDocumentList.size() > 0) {
                for (int value = 0; value < uploadDocumentList.size(); value++) {
                    UploadDocument uploadDocument = uploadDocumentList.get(value);
                    File file = new File(uploadDocument.getFilePath());
                    builder.addBinaryBody("file_" + (value + 1), new FileInputStream(file),
                            ContentType.create(uploadDocument.getMimeType()), file.getName());
                }
            }

            HttpEntity multipart = builder.build();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            multipart.writeTo(outStream);
            request.POST(HttpRequest.BodyPublishers.ofByteArray(outStream.toByteArray()));
            outStream.close();
            request.header("Content-Type", multipart.getContentType().getValue());
            addTimeout(request);
            return httpClient.send(request.build(), BodyHandlers.ofString());
        }catch (Exception ex) {
            logger.log(Level.SEVERE, Map.of("message", "Error calling api", "error", ex, "http_request_uri", fullPath, "body", body));
            throw new RuntimeException(ex);
        }
    }

    private HttpRequest prepareRequest(String path, Map<String, String> headers, String body) {
        String fullPath = basePath + path;
        logRequest(fullPath, headers, body);
        var request = HttpRequest.newBuilder().uri(URI.create(fullPath));
        headers.entrySet().forEach(entry -> request.header(entry.getKey(), entry.getValue()));
        addTimeout(request);
        return request.POST(HttpRequest.BodyPublishers.ofString(body)).build();
    }

    /**
     *
     *Add request timeout in API request.
     */
    private void addTimeout(HttpRequest.Builder request) {
        if(timeout>0)
            request.timeout(Duration.ofSeconds(timeout));
    }
    /**
     * Makes the call to the sila PUT API.
     *
     * @param path
     * @param headers
     * @param body
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @SuppressWarnings("all")
    public HttpResponse callApiPut(String path, Map<String, String> headers, String body)
            throws IOException, InterruptedException {
        headers.put("User-Agent", PRODUCT + '/' + VERSION);
        String fullPath = basePath + path;
        logRequest(fullPath, headers, body);
        HttpRequest finalRequest = prepareRequestPut(path, headers, body);

        return httpClient.send(finalRequest, BodyHandlers.ofString());
    }
    /**
     * Makes the final call to the sila PUT API.
     *
     * @param path
     * @param headers
     * @param body
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private HttpRequest prepareRequestPut(String path, Map<String, String> headers, String body) {
        var request = HttpRequest.newBuilder().uri(URI.create(basePath + path));

        headers.entrySet().forEach(entry -> request.header(entry.getKey(), entry.getValue()));

        return request.PUT(HttpRequest.BodyPublishers.ofString(body)).build();
    }

    /**
     * Makes the call to the sila GET API.
     *
     * @param path
     * @param headers
     * @param body
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @SuppressWarnings("all")
    public HttpResponse callApiGet(String path, Map<String, String> headers, String body)
            throws IOException, InterruptedException {
        String fullPath = basePath + path;
        logRequest(fullPath, headers, body);
        headers.put("User-Agent", PRODUCT + '/' + VERSION);
        HttpRequest finalRequest = prepareRequestGet(path, headers, body);
        return httpClient.send(finalRequest, BodyHandlers.ofString());
    }

    /**
     * Makes the final call to the sila GET API.
     *
     */
    private HttpRequest prepareRequestGet(String path, Map<String, String> headers, String body) {
        var request = HttpRequest.newBuilder().uri(URI.create(basePath + path));

        headers.entrySet().forEach(entry -> request.header(entry.getKey(), entry.getValue()));
        return request.method("GET", HttpRequest.BodyPublishers.ofString(body)).build();
    }


    private void logRequest(String path, Map<String, String> headers, String body) {
        Map<String, Object> normHeaders = ResponseUtil.normHeaderMap(headers != null ? headers : Collections.emptyMap());
        logger.log(Level.INFO, Map.of("body", body != null ? body : "",
                "http_request_uri", path,
                "headers", normHeaders));
    }
}
