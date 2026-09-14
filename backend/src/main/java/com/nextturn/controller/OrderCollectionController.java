package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff/collection")
@CrossOrigin
public class OrderCollectionController {

    private final OrderRepository orderRepository;

    public OrderCollectionController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PutMapping("/{id}/collect")
    public ResponseEntity<Order> collectOrder(
            @PathVariable Long id) {

        return orderRepository.findById(id)
                .map(order -> {

                    if (!"Ready".equals(order.getOrderStatus())) {
                        return ResponseEntity.badRequest().body(order);
                    }

                    order.setOrderStatus("Collected");

                    return ResponseEntity.ok(
                            orderRepository.save(order)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }
                     }
