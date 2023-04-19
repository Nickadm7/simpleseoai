package com.filinnv.simpleseoai.service;

import com.filinnv.simpleseoai.dto.SeoResponseDto;
import com.filinnv.simpleseoai.mapper.SeoResponseMapper;
import com.filinnv.simpleseoai.model.SeoRequest;
import com.filinnv.simpleseoai.model.SeoResponse;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class SeoServiceImpl implements SeoService {
    private final static String API_KEY = "KEY_PUT_HERE";
    private final static String URL = "https://api.openai.com/v1/completions";
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    @Override
    public SeoResponseDto makeRequest(SeoRequest seoRequest) {
        String requestBody = prepareRequestBody(seoRequest.getText());
        String responseBody = sendRequestToChatGpt(requestBody);
        SeoResponse seoResponse = gson.fromJson(responseBody, SeoResponse.class);
        return SeoResponseMapper.toSeoResponseDto(seoResponse, seoRequest.getText());
    }

    private String prepareRequestBody(String prompt) {
        JSONObject body = new JSONObject();
        body.put("model", "text-davinci-003");
        body.put("prompt", prompt);
        body.put("max_tokens", 5);
        body.put("temperature", 1.0);
        return body.toString();
    }

    private String sendRequestToChatGpt(String requestBody) {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
