package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/staff/dashboard")
@CrossOrigin
public class StaffDashboardController {

    private final OrderRepository orderRepository;

    public StaffDashboardController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/summary")
    public Map<String, Object> getDashboardSummary() {

        long totalOrders = orderRepository.count();

        long pendingPayments = orderRepository.findByPaymentStatus(
                "Pending Verification"
        ).size();

        long completedOrders = orderRepository.findByOrderStatus(
                "Completed"
        ).size();

        double revenue = orderRepository.findAll().stream()
                .filter(order ->
                        "Completed".equals(order.getOrderStatus())
                        || "Collected".equals(order.getOrderStatus()))
                .mapToDouble(Order::getTotalAmount)
                .sum();

        Map<String, Object> summary = new HashMap<>();

        summary.put("totalOrders", totalOrders);
        summary.put("pendingPayments", pendingPayments);
        summary.put("completedOrders", completedOrders);
        summary.put("totalRevenue", revenue);

        return summary;
    }
          }
