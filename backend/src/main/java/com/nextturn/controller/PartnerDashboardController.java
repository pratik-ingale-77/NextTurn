package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partner")
@CrossOrigin
public class PartnerDashboardController {

    private final OrderRepository orderRepository;

    public PartnerDashboardController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    public List<Order> getBusinessOrders() {

        return orderRepository.findAll().stream()
                .filter(order ->
                        "Completed".equals(order.getOrderStatus())
                        || "Collected".equals(order.getOrderStatus()))
                .toList();
    }

    @GetMapping("/total-orders")
    public long getTotalOrders() {

        return orderRepository.findAll().stream()
                .filter(order ->
                        "Completed".equals(order.getOrderStatus())
                        || "Collected".equals(order.getOrderStatus()))
                .count();
    }
}
