package com.filinnv.simpleseoai.model;

import lombok.Data;

@Data
public class Choice {
    private String text;
    private Integer index;
    private Integer logprobs;
    private String finish_reason;
}
