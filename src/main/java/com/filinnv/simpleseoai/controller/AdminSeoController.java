package com.filinnv.simpleseoai.controller;

import com.filinnv.simpleseoai.dto.RequestHistoryDtoAdmin;
import com.filinnv.simpleseoai.service.SeoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/admin/history")
@RequiredArgsConstructor
public class AdminSeoController {
    private final SeoService seoService;

    @GetMapping("/{id}")
    RequestHistoryDtoAdmin getSeoResponseById(@Positive @PathVariable Long id) {
        log.info("Get request by id: {}", id);
        return seoService.getSeoResponseByIdByAdmin(id);
    }

    @GetMapping(value = "all")
    List<RequestHistoryDtoAdmin> getAllSeoResponse() {
        log.info("Get request find all");
        return seoService.getAllSeoResponseByAdmin();
    }
}
