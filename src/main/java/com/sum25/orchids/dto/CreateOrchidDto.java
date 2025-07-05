package com.sum25.orchids.dto;

import lombok.Data;

@Data
public class CreateOrchidDto {
    private Boolean isNatural;
    private String orchidDescription;
    private String orchidName;
    private String orchidUrl;
    private Integer price;
    private String orchidType;
}
