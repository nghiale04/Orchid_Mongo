package com.sum25.orchids.controller;

import com.sum25.orchids.dto.OrderDetailDto;
import com.sum25.orchids.dto.OrderDto;
import com.sum25.orchids.dto.ResponseDTO;
import com.sum25.orchids.repository.OrderRepository;
import com.sum25.orchids.services.JwtService;
import com.sum25.orchids.services.OrdersService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrdersService ordersService;
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody List<OrderDetailDto> orchidDetailDto) {
        ordersService.createOrder(orchidDetailDto);
        return ResponseEntity.ok("Order created successfully");
    }
    @GetMapping
    public ResponseEntity<ResponseDTO> getAllOrders() {
        List<OrderDto> orderDtos = ordersService.getAllOrders();
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .statusCode("200")
                        .message("Orders fetched successfully")
                        .data(orderDtos)
                        .build()
        );
    }

}
