package com.filinnv.simpleseoai.service;

import com.filinnv.simpleseoai.dto.RequestHistoryDto;
import com.filinnv.simpleseoai.dto.RequestHistoryDtoAdmin;
import com.filinnv.simpleseoai.dto.SeoRequestDto;
import com.filinnv.simpleseoai.exception.NotFoundException;
import com.filinnv.simpleseoai.mapper.Mapper;
import com.filinnv.simpleseoai.model.RequestHistory;
import com.filinnv.simpleseoai.model.SeoResponse;
import com.filinnv.simpleseoai.repository.SeoRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeoServiceImpl implements SeoService {
    private final SeoRepository seoRepository;
    @Value("${openai.apikey}")
    private String API_KEY;
    @Value("${openai.url}")
    private String URL;
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    @Override
    public RequestHistoryDto makeRequest(SeoRequestDto seoRequestDto) {
        String requestBody = prepareRequestBody(seoRequestDto.getText());
        String responseBody = sendRequestToChatGpt(requestBody);
        SeoResponse seoResponse = gson.fromJson(responseBody, SeoResponse.class);
        saveToHistory(seoRequestDto.getText(), seoResponse.getChoices().get(0).getText());
        return Mapper.toSeoResponseDto(seoResponse, seoRequestDto.getText());
    }

    @Override
    public RequestHistoryDtoAdmin getSeoResponseByIdByAdmin(Long id) {
        Optional<RequestHistory> buffer = seoRepository.findById(id);
        if (buffer.isPresent()) {
            return Mapper.historyToSeoResponseDtoAdmin(buffer.get());
        } else {
            throw new NotFoundException("Not found record by id: " + id);
        }
    }

    @Override
    public List<RequestHistoryDtoAdmin> getAllSeoResponseByAdmin() {
        List<RequestHistory> allHistory = seoRepository.findAll();
        return Mapper.historyToSeoResponseDtoAdmin(allHistory);
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

    private void saveToHistory(String request, String response) {
        RequestHistory requestHistory = RequestHistory.builder()
                .created(LocalDateTime.now())
                .request(request)
                .response(response)
                .build();
        RequestHistory save = seoRepository.save(requestHistory);
        log.info("Successful save to history id: {}, request: {}", save.getId(), request);
    }
}
