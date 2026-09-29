package com.example.College.Event.Registration.System.repository;

import com.example.College.Event.Registration.System.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}