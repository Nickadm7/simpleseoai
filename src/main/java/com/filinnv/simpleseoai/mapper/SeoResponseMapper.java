package com.filinnv.simpleseoai.mapper;

import com.filinnv.simpleseoai.dto.SeoResponseDto;
import com.filinnv.simpleseoai.model.SeoResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)public class SeoResponseMapper {
    public static SeoResponseDto toSeoResponseDto (SeoResponse seoResponse, String inputPrompt) {
        if (seoResponse != null) {
            if (seoResponse.getChoices().size() != 0) {
                return new SeoResponseDto(inputPrompt, seoResponse.getChoices().get(0).getText());
            }
        }
        return new SeoResponseDto(inputPrompt, "Answer not found");
    }
}
