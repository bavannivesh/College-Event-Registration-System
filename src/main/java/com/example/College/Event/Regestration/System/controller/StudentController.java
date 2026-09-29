package com.example.College.Event.Registration.System.controller;

import com.example.College.Event.Registration.System.entity.Student;
import com.example.College.Event.Registration.System.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(
            @Valid @RequestBody Student student) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentService.createStudent(student));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(
                studentService.getAllStudents()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                studentService.getStudentById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {

        return ResponseEntity.ok(
                studentService.updateStudent(id, student)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok(
                "Student deleted successfully"
        );
    }
}