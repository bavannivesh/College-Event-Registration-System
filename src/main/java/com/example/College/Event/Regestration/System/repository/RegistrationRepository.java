package com.example.College.Event.Registration.System.repository;

import com.example.College.Event.Registration.System.entity.Registration;
import com.example.College.Event.Registration.System.entity.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    boolean existsByStudentIdAndEventId(Long studentId, Long eventId);

    long countByEventIdAndStatus(Long eventId, RegistrationStatus status);

    List<Registration> findByEventId(Long eventId);

    List<Registration> findByStudentId(Long studentId);

    void deleteByEventId(Long eventId);
}