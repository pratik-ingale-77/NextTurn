package com.nextturn.controller;

import com.nextturn.model.Student;
import com.nextturn.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student-login")
@CrossOrigin
public class StudentLoginController {

    private final StudentRepository studentRepository;

    public StudentLoginController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public ResponseEntity<Student> login(@RequestBody Student loginStudent) {

        return studentRepository.findByEmail(loginStudent.getEmail())
                .filter(student ->
                        student.getPassword().equals(loginStudent.getPassword()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }
}
