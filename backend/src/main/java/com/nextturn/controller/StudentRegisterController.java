package com.nextturn.controller;

import com.nextturn.model.Student;
import com.nextturn.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student-register")
@CrossOrigin
public class StudentRegisterController {

    private final StudentRepository studentRepository;

    public StudentRegisterController(
            StudentRepository studentRepository
    ) {
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public ResponseEntity<?> register(
            @RequestBody Student student
    ) {

        // Check Gmail already exists
        if (studentRepository.existsByEmail(student.getEmail())) {

            return ResponseEntity
                    .status(409)
                    .body("This Gmail is already registered.");
        }


        // Check Student ID already exists
        if (studentRepository.existsByStudentId(student.getStudentId())) {

            return ResponseEntity
                    .status(409)
                    .body("This Student ID is already registered.");
        }


        // Save new student
        Student savedStudent =
                studentRepository.save(student);


        return ResponseEntity.ok(savedStudent);
    }
}
