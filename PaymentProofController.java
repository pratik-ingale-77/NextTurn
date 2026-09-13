package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment-proof")
@CrossOrigin
public class PaymentProofController {

    private final OrderRepository orderRepository;

    public PaymentProofController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> submitPaymentProof(
            @PathVariable Long id,
            @RequestParam String proof) {

        return orderRepository.findById(id)
                .map(order -> {

                    order.setPaymentProof(proof);
                    order.setPaymentStatus("Pending Verification");

                    return ResponseEntity.ok(
                            orderRepository.save(order)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
