package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/revenue")
@CrossOrigin
public class RevenueController {

    private final OrderRepository orderRepository;

    public RevenueController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public double getTotalRevenue() {

        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .filter(order ->
                        "Completed".equals(order.getOrderStatus())
                        || "Collected".equals(order.getOrderStatus()))
                .mapToDouble(Order::getTotalAmount)
                .sum();
    }

    @GetMapping("/today")
    public double getTodayRevenue() {

        LocalDate today = LocalDate.now();

        return orderRepository.findAll().stream()
                .filter(order ->
                        ("Completed".equals(order.getOrderStatus())
                        || "Collected".equals(order.getOrderStatus()))
                        && order.getCreatedAt().toLocalDate().equals(today))
                .mapToDouble(Order::getTotalAmount)
                .sum();
    }
          }
