package com.filinnv.simpleseoai.model;


import lombok.Data;

import java.util.List;

@Data
public class SeoResponse {
    private String id;
    private String object;
    private String created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
}

