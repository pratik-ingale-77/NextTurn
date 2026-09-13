package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderStatusController {

    private final OrderRepository orderRepository;

    public OrderStatusController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return orderRepository.findById(id)
                .map(order -> {
                    order.setOrderStatus(status);
                    return ResponseEntity.ok(orderRepository.save(order));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
