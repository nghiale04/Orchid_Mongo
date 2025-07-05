package com.sum25.orchids.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseDTO<T> {
    private String statusCode;
    private String message;
    private T data;
}
