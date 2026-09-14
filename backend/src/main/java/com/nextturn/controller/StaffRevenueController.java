package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/staff/revenue")
@CrossOrigin
public class StaffRevenueController {

    private final OrderRepository orderRepository;

    public StaffRevenueController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/summary")
    public Map<String, Object> getRevenueSummary() {

        LocalDate today = LocalDate.now();
        YearMonth month = YearMonth.now();

        double todayRevenue = 0;
        double monthlyRevenue = 0;
        double totalRevenue = 0;

        for (Order order : orderRepository.findAll()) {

            boolean completed =
                    "Completed".equals(order.getOrderStatus())
                    || "Collected".equals(order.getOrderStatus());

            if (!completed) {
                continue;
            }

            double amount = order.getTotalAmount();
            totalRevenue += amount;

            if (order.getCreatedAt().toLocalDate().equals(today)) {
                todayRevenue += amount;
            }

            if (YearMonth.from(order.getCreatedAt()).equals(month)) {
                monthlyRevenue += amount;
            }
        }

        Map<String, Object> result = new HashMap<>();

        result.put("todayRevenue", todayRevenue);
        result.put("monthlyRevenue", monthlyRevenue);
        result.put("totalRevenue", totalRevenue);

        return result;
    }
}
