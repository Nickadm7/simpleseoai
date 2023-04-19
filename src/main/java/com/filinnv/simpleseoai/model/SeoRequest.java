package com.filinnv.simpleseoai.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class SeoRequest {
    @NotBlank(message = "Incoming request text cannot be empty")
    @Size(min = 1, max = 4096, message = "Length of the text should be from 0 to 4096")
    private String text;
}