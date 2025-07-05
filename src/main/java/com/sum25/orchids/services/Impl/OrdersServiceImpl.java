package com.sum25.orchids.services.Impl;

import com.sum25.orchids.dto.OrderDetailDto;
import com.sum25.orchids.dto.OrderDto;
import com.sum25.orchids.entity.Accounts;
import com.sum25.orchids.entity.OrderDetails;
import com.sum25.orchids.entity.Orders;
import com.sum25.orchids.repository.OrchidRepository;
import com.sum25.orchids.repository.OrderDetailRepository;
import com.sum25.orchids.services.JwtService;
import com.sum25.orchids.services.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.sum25.orchids.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private OrderRepository orderRepository;
    private JwtService jwtService;
    private OrchidRepository orchidRepository;
    private OrderDetailRepository orderDetailRepository;

    @Override
    public void createOrder(List<OrderDetailDto> orchidDetailDto) {
        Accounts accounts = jwtService.getCurrentAccount();
        if (accounts == null) {
            throw new IllegalStateException("No account found for the current user");
        }
        Orders orders = new Orders();
        orders.setAccount(accounts);
        orders.setOrderDate(java.time.LocalDate.now());
        orders.setStatus("PENDING");
        orderRepository.save(orders);

        int totalQuantity = 0;

        for (OrderDetailDto orderDetailDto : orchidDetailDto) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrder(orders);
            orderDetails.setPrice(orderDetailDto.getPrice());
            orderDetails.setQuantity(orderDetailDto.getQuantity());
            orderDetails.setOrchid(orchidRepository.findById(orderDetailDto.getId()).get());
            orderDetailRepository.save(orderDetails);
            totalQuantity += (orderDetailDto.getQuantity()* orderDetailDto.getPrice());
        }
        orders.setTotalAmount(totalQuantity);
        orderRepository.save(orders);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        Accounts accounts = jwtService.getCurrentAccount();
        if (accounts == null) {
            throw new IllegalStateException("No account found for the current user");
        }
        List<Orders> ordersList = orderRepository.findAllByAccount(accounts);
        if (ordersList.isEmpty()) {
            throw new IllegalStateException("No orders found for the current user");
        }
        List<OrderDto> listOrders = new ArrayList<>();
        for (Orders orders : ordersList) {

            List<OrderDetails> listOrderDetai = orderDetailRepository.findByOrder(orders);
            if (listOrderDetai.isEmpty()) {
                throw new IllegalStateException("No order details found for order ID: " + orders.getId());
            }
            List<OrderDetailDto> listOrderDetailDto = new ArrayList<>();
            for (OrderDetails orderDetails : listOrderDetai) {
                OrderDetailDto orderDetailDto = new OrderDetailDto();
                orderDetailDto.setId(orderDetails.getOrchid().getId());
                orderDetailDto.setPrice(orderDetails.getPrice());
                orderDetailDto.setQuantity(orderDetails.getQuantity());
                orderDetailDto.setOrchidName(orderDetails.getOrchid().getOrchidName());
                listOrderDetailDto.add(orderDetailDto);
            }

            OrderDto orderDto = new OrderDto();
            orderDto.setId(orders.getId());
            orderDto.setOrderDate(orders.getOrderDate().toString());
            orderDto.setOrderTotal(orders.getTotalAmount());
            orderDto.setOrderStatus(orders.getStatus());
            orderDto.setOrderDetailDtos(listOrderDetailDto);
            listOrders.add(orderDto);
        }
        return listOrders;
    }

}
