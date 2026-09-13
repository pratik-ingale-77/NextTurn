package com.nextturn.repository;

import com.nextturn.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStudentId(String studentId);

    List<Order> findByOrderStatus(String orderStatus);

    List<Order> findByPaymentStatus(String paymentStatus);
}
