package com.nextturn.controller;

import com.nextturn.model.Staff;
import com.nextturn.repository.StaffRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/setup")
@CrossOrigin
public class CreateStaffController {

    private final StaffRepository staffRepository;

    public CreateStaffController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @GetMapping("/create-staff")
    public ResponseEntity<String> createStaff() {

        if (staffRepository.existsByStaffId("STAFF001")) {
            return ResponseEntity.ok("Staff account already exists.");
        }

        Staff staff = new Staff(
                "Canteen Staff",
                "STAFF001",
                "staff123"
        );

        staffRepository.save(staff);

        return ResponseEntity.ok(
                "Staff account created successfully. ID: STAFF001"
        );
    }
          }
