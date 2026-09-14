package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff/history")
@CrossOrigin
public class StaffOrderHistoryController {

    private final OrderRepository orderRepository;

    public StaffOrderHistoryController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<Order> getOrderHistory() {

        return orderRepository.findAll()
                .stream()
                .filter(order ->
                        "Completed".equals(order.getOrderStatus())
                        || "Collected".equals(order.getOrderStatus()))
                .toList();
    }
}
