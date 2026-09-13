package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff/tokens")
@CrossOrigin
public class StaffTokenController {

    private final OrderRepository orderRepository;

    public StaffTokenController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PutMapping("/{id}/generate")
    public ResponseEntity<Order> generateToken(
            @PathVariable Long id) {

        return orderRepository.findById(id)
                .map(order -> {

                    if (!"Verified".equals(order.getPaymentStatus())) {
                        return ResponseEntity.badRequest().body(order);
                    }

                    int token =
                            (int) (Math.random() * 9000) + 1000;

                    order.setTokenNumber(token);
                    order.setOrderStatus("Preparing");

                    return ResponseEntity.ok(
                            orderRepository.save(order)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
