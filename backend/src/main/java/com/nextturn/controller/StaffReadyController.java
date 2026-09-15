package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff/ready")
@CrossOrigin
public class StaffReadyController {

    private final OrderRepository orderRepository;

    public StaffReadyController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> markAsReady(
            @PathVariable Long id) {

        return orderRepository.findById(id)
                .map(order -> {

                    if (order.getTokenNumber() == null) {
                        return ResponseEntity.badRequest().body(order);
                    }

                    if (!"Preparing".equals(order.getOrderStatus())) {
                        return ResponseEntity.badRequest().body(order);
                    }

                    order.setOrderStatus("Ready");

                    return ResponseEntity.ok(
                            orderRepository.save(order)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }
  }
