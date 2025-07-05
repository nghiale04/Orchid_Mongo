package com.sum25.orchids.services;

import com.sum25.orchids.dto.OrderDetailDto;
import com.sum25.orchids.dto.OrderDto;

import java.util.List;

public interface OrdersService {
    void createOrder(List<OrderDetailDto> orchidDetailDto);

    public List<OrderDto> getAllOrders();

}
