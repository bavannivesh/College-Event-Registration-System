package com.example.College.Event.Registration.System.service;

import com.example.College.Event.Registration.System.entity.Student;
import com.example.College.Event.Registration.System.exception.ResourceNotFoundException;
import com.example.College.Event.Registration.System.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with ID: " + id
                        ));
    }

    public Student updateStudent(Long id, Student student) {

        Student existing = getStudentById(id);

        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setDepartment(student.getDepartment());
        existing.setRegisterNumber(student.getRegisterNumber());

        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id) {

        Student student = getStudentById(id);

        studentRepository.delete(student);
    }
}