package com.filinnv.simpleseoai.service;

import com.filinnv.simpleseoai.dto.SeoResponseDto;
import com.filinnv.simpleseoai.model.SeoRequest;

public interface SeoService {
    SeoResponseDto makeRequest(SeoRequest seoRequest);
}