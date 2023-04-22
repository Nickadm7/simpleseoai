package com.filinnv.simpleseoai.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SeoRequestDto {
    @NotBlank(message = "Incoming request text cannot be empty")
    @Size(min = 1, max = 4096, message = "Length of the text should be from 0 to 4096")
    private String text;
}
