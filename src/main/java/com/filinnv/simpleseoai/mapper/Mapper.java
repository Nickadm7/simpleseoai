package com.filinnv.simpleseoai.mapper;

import com.filinnv.simpleseoai.dto.RequestHistoryDto;
import com.filinnv.simpleseoai.dto.RequestHistoryDtoAdmin;
import com.filinnv.simpleseoai.model.RequestHistory;
import com.filinnv.simpleseoai.model.SeoResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mapper {
    public static RequestHistoryDto toSeoResponseDto(SeoResponse seoResponse, String inputPrompt) {
        if (seoResponse != null) {
            if (seoResponse.getChoices().size() != 0) {
                return RequestHistoryDto.builder()
                        .prompt(inputPrompt)
                        .answer(seoResponse.getChoices().get(0).getText())
                        .build();
            }
        }
        return RequestHistoryDto.builder()
                .prompt(inputPrompt)
                .answer("Answer not found")
                .build();
    }

    public static RequestHistoryDto historyToSeoResponseDto(RequestHistory requestHistory) {
        return RequestHistoryDto.builder()
                .prompt(requestHistory.getRequest())
                .answer(requestHistory.getResponse())
                .build();
    }

    public static RequestHistoryDtoAdmin historyToSeoResponseDtoAdmin(RequestHistory requestHistory) {
        return RequestHistoryDtoAdmin.builder()
                .id(requestHistory.getId())
                .created(requestHistory.getCreated())
                .prompt(requestHistory.getRequest())
                .answer(requestHistory.getResponse())
                .build();
    }

    public static List<RequestHistoryDtoAdmin> historyToSeoResponseDtoAdmin(List<RequestHistory> requestHistories) {
        return requestHistories.stream()
                .map(Mapper::historyToSeoResponseDtoAdmin)
                .collect(Collectors.toList());
    }
}
