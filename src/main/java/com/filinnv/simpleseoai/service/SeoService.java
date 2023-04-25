package com.filinnv.simpleseoai.service;

import com.filinnv.simpleseoai.dto.SeoRequestDto;
import com.filinnv.simpleseoai.dto.RequestHistoryDto;
import com.filinnv.simpleseoai.dto.RequestHistoryDtoAdmin;

import java.util.List;

public interface SeoService {
    RequestHistoryDto makeRequest(SeoRequestDto seoRequestDto);

    List<RequestHistoryDtoAdmin> getAllSeoResponseByAdmin();

    RequestHistoryDtoAdmin getSeoResponseByIdByAdmin(Long id);
}