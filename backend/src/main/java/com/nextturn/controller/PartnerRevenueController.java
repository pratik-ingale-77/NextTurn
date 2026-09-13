package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;

@RestController
@RequestMapping("/api/partner/revenue")
@CrossOrigin
public class PartnerRevenueController {

    private final OrderRepository orderRepository;

    public PartnerRevenueController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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

    @GetMapping("/month")
    public double getMonthlyRevenue() {

        YearMonth currentMonth = YearMonth.now();

        return orderRepository.findAll().stream()
                .filter(order ->
                        ("Completed".equals(order.getOrderStatus())
                        || "Collected".equals(order.getOrderStatus()))
                        && YearMonth.from(order.getCreatedAt()).equals(currentMonth))
                .mapToDouble(Order::getTotalAmount)
                .sum();
    }

    @GetMapping("/total")
    public double getTotalRevenue() {

        return orderRepository.findAll().stream()
                .filter(order ->
                        "Completed".equals(order.getOrderStatus())
                        || "Collected".equals(order.getOrderStatus()))
                .mapToDouble(Order::getTotalAmount)
                .sum();
    }
          }
