package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/create-order")
@CrossOrigin
public class StudentCreateOrderController {

    private final OrderRepository orderRepository;

    public StudentCreateOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody Order order) {

        // Set initial order status
        order.setPaymentStatus("Pending Verification");
        order.setOrderStatus("Payment Submitted");

        // Token is generated only after staff verifies payment
        order.setTokenNumber(null);

        // Save order to MySQL
        Order savedOrder = orderRepository.save(order);

        return ResponseEntity.ok(savedOrder);
    }
}
