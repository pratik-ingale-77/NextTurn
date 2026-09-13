package com.nextturn.repository;

import com.nextturn.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    Optional<Staff> findByStaffId(String staffId);

    boolean existsByStaffId(String staffId);
}
