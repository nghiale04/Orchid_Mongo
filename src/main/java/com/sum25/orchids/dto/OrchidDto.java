package com.sum25.orchids.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrchidDto {
    private String id;
    private Boolean isNatural;
    private String orchidDescription;
    private String orchidName;
    private String orchidUrl;
    private Integer price;
    private String orchidType;
}
