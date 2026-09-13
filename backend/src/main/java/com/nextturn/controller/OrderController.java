package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public List<Order> getOrdersByStudent(@PathVariable String studentId) {
        return orderService.getOrdersByStudent(studentId);
    }

    @GetMapping("/status/{status}")
    public List<Order> getOrdersByStatus(@PathVariable String status) {
        return orderService.getOrdersByStatus(status);
    }

    @GetMapping("/payment-status/{status}")
    public List<Order> getOrdersByPaymentStatus(@PathVariable String status) {
        return orderService.getOrdersByPaymentStatus(status);
    }
}
