package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff/orders")
@CrossOrigin
public class StaffOrderController {

    private final OrderRepository orderRepository;

    public StaffOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/pending-payment")
    public List<Order> getPendingPayments() {
        return orderRepository.findByPaymentStatus("Pending Verification");
    }

    @GetMapping("/preparing")
    public List<Order> getPreparingOrders() {
        return orderRepository.findByOrderStatus("Preparing");
    }

    @GetMapping("/ready")
    public List<Order> getReadyOrders() {
        return orderRepository.findByOrderStatus("Ready");
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Order> completeOrder(@PathVariable Long id) {

        return orderRepository.findById(id)
                .map(order -> {
                    order.setOrderStatus("Completed");
                    return ResponseEntity.ok(orderRepository.save(order));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
