package com.nextturn.controller;

import com.nextturn.model.Student;
import com.nextturn.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/profile")
@CrossOrigin
public class StudentProfileController {

    private final StudentRepository studentRepository;

    public StudentProfileController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getProfile(
            @PathVariable String studentId) {

        return studentRepository.findAll()
                .stream()
                .filter(student ->
                        student.getStudentId().equals(studentId))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
