package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/notifications")
@CrossOrigin
public class StudentNotificationController {

    private final OrderRepository orderRepository;

    public StudentNotificationController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{studentId}")
    public String getNotification(
            @PathVariable String studentId) {

        List<Order> orders =
                orderRepository.findByStudentId(studentId);

        if (orders.isEmpty()) {
            return "No notifications";
        }

        Order latestOrder = orders.get(orders.size() - 1);

        if ("Ready".equals(latestOrder.getOrderStatus())) {
            return "Your food is ready. Token: "
                    + latestOrder.getTokenNumber();
        }

        if ("Preparing".equals(latestOrder.getOrderStatus())) {
            return "Your order is being prepared. Token: "
                    + latestOrder.getTokenNumber();
        }

        if ("Payment Verified".equals(latestOrder.getOrderStatus())) {
            return "Payment verified. Your token will be generated soon.";
        }

        return "Your order is being processed.";
    }
}
