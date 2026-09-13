package com.nextturn.service;

import com.nextturn.model.Staff;
import com.nextturn.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Staff registerStaff(Staff staff) {
        if (staffRepository.existsByStaffId(staff.getStaffId())) {
            throw new RuntimeException("Staff ID already registered");
        }

        return staffRepository.save(staff);
    }

    public Optional<Staff> getStaffById(String staffId) {
        return staffRepository.findByStaffId(staffId);
    }
      }
