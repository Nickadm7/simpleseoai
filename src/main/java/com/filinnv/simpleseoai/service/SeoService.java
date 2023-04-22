package com.filinnv.simpleseoai.service;

import com.filinnv.simpleseoai.dto.SeoRequestDto;
import com.filinnv.simpleseoai.dto.SeoResponseDto;

public interface SeoService {
    SeoResponseDto makeRequest(SeoRequestDto seoRequestDto);
}