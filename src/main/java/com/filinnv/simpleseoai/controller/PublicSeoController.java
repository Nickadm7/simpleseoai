package com.filinnv.simpleseoai.controller;

import com.filinnv.simpleseoai.dto.SeoResponseDto;
import com.filinnv.simpleseoai.model.SeoRequest;
import com.filinnv.simpleseoai.service.SeoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class PublicSeoController {
    private final SeoService seoService;

    @PostMapping
    public SeoResponseDto makeRequest(@Validated @RequestBody SeoRequest seoRequest) {
        log.info("POST request text: " + seoRequest.getText());
        return seoService.makeRequest(seoRequest);
    }
}