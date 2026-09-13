package com.nextturn.controller;

import com.nextturn.model.Order;
import com.nextturn.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/orders")
@CrossOrigin
public class StudentOrderController {

    private final OrderRepository orderRepository;

    public StudentOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{studentId}")
    public List<Order> getStudentOrders(
            @PathVariable String studentId) {

        return orderRepository.findByStudentId(studentId);
    }

    @GetMapping("/current/{studentId}")
    public ResponseEntity<Order> getCurrentOrder(
            @PathVariable String studentId) {

        List<Order> orders =
                orderRepository.findByStudentId(studentId);

        return orders.stream()
                .filter(order ->
                        !"Completed".equals(order.getOrderStatus()))
                .reduce((first, second) -> second)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
