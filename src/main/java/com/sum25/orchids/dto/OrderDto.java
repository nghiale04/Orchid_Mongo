package com.sum25.orchids.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private String id;
    private String orderDate;
    private String orderStatus;
    private Integer orderTotal;
    private List<OrderDetailDto> orderDetailDtos;

}
