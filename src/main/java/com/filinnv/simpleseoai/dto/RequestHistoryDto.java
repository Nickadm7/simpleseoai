package com.filinnv.simpleseoai.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestHistoryDto {
    private String prompt;

    private String answer;
}
