package com.sum25.orchids.dto;

import lombok.Data;

@Data
public class OrderDetailDto {
    private String id;
    private String orchidName;
    private Integer quantity;
    private Integer price;
}
