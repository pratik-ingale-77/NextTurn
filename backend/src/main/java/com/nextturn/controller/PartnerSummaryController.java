package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/partner/summary")
@CrossOrigin
public class PartnerSummaryController {

    private final OrderRepository orderRepository;

    public PartnerSummaryController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public Map<String, Object> getSummary() {

        long totalOrders = 0;
        double totalRevenue = 0;

        for (Order order : orderRepository.findAll()) {

            boolean completed =
                    "Completed".equals(order.getOrderStatus())
                    || "Collected".equals(order.getOrderStatus());

            if (completed) {
                totalOrders++;
                totalRevenue += order.getTotalAmount();
            }
        }

        Map<String, Object> result = new HashMap<>();

        result.put("totalOrders", totalOrders);
        result.put("totalRevenue", totalRevenue);

        return result;
    }
}
