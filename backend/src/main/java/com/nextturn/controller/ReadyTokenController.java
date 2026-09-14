package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff/ready")
@CrossOrigin
public class ReadyTokenController {

    private final OrderRepository orderRepository;

    public ReadyTokenController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PutMapping("/{id}/announce")
    public ResponseEntity<String> announceReady(
            @PathVariable Long id) {

        return orderRepository.findById(id)
                .map(order -> {

                    if (!"Ready".equals(order.getOrderStatus())) {
                        return ResponseEntity.badRequest()
                                .body("Order is not marked Ready yet.");
                    }

                    return ResponseEntity.ok(
                            "Token number "
                                    + order.getTokenNumber()
                                    + " food is ready."
                    );
                })
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }
}
