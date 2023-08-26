package com.example.demo.controller;
import com.example.demo.domain.Order;
import com.example.demo.dto.OrderItemRequestDto;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public Order placeOrder(@RequestBody List<OrderItemRequestDto> orderItems) {
        return orderService.placeOrder(orderItems);
    }
}
