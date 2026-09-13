package com.nextturn.controller;

import com.nextturn.model.Staff;
import com.nextturn.repository.StaffRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff-login")
@CrossOrigin
public class StaffLoginController {

    private final StaffRepository staffRepository;

    public StaffLoginController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @PostMapping
    public ResponseEntity<Staff> login(@RequestBody Staff loginStaff) {

        return staffRepository.findByStaffId(loginStaff.getStaffId())
                .filter(staff ->
                        staff.getPassword().equals(loginStaff.getPassword()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }
}
