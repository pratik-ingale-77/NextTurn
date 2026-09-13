package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff/payments")
@CrossOrigin
public class StaffPaymentController {

    private final OrderRepository orderRepository;

    public StaffPaymentController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PutMapping("/{id}/verify")
    public ResponseEntity<Order> verifyPayment(
            @PathVariable Long id) {

        return orderRepository.findById(id)
                .map(order -> {

                    order.setPaymentStatus("Verified");
                    order.setOrderStatus("Payment Verified");

                    return ResponseEntity.ok(
                            orderRepository.save(order)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
