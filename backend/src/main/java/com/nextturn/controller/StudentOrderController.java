package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/student/orders")
@CrossOrigin
public class StudentOrderController {

    private final OrderRepository orderRepository;

    public StudentOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Get all orders for a student
    @GetMapping("/{studentId}")
    public List<Order> getStudentOrders(
            @PathVariable String studentId) {

        List<Order> orders =
                orderRepository.findByStudentId(studentId);

        // Latest order first
        orders.sort(
                Comparator.comparing(
                        Order::getId,
                        Comparator.nullsLast(Comparator.reverseOrder())
                )
        );

        return orders;
    }

    // Get the latest active order for a student
    @GetMapping("/current/{studentId}")
    public ResponseEntity<Order> getCurrentOrder(
            @PathVariable String studentId) {

        List<Order> orders =
                orderRepository.findByStudentId(studentId);

        // Sort latest order first
        orders.sort(
                Comparator.comparing(
                        Order::getId,
                        Comparator.nullsLast(Comparator.reverseOrder())
                )
        );

        return orders.stream()
                .filter(order ->
                        !"Completed".equalsIgnoreCase(
                                order.getOrderStatus()
                        )
                )
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
