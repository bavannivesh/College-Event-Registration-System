package com.example.College.Event.Registration.System.service;

import com.example.College.Event.Registration.System.entity.Event;
import com.example.College.Event.Registration.System.entity.Registration;
import com.example.College.Event.Registration.System.entity.RegistrationStatus;
import com.example.College.Event.Registration.System.entity.Student;
import com.example.College.Event.Registration.System.exception.DuplicateRegistrationException;
import com.example.College.Event.Registration.System.exception.EventFullException;
import com.example.College.Event.Registration.System.exception.ResourceNotFoundException;
import com.example.College.Event.Registration.System.repository.EventRepository;
import com.example.College.Event.Registration.System.repository.RegistrationRepository;
import com.example.College.Event.Registration.System.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;
    private final EventRepository eventRepository;

    public RegistrationService(
            RegistrationRepository registrationRepository,
            StudentRepository studentRepository,
            EventRepository eventRepository) {

        this.registrationRepository = registrationRepository;
        this.studentRepository = studentRepository;
        this.eventRepository = eventRepository;
    }

    // =========================
    // REGISTER STUDENT FOR EVENT
    // =========================

    public Registration registerStudent(Long studentId, Long eventId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + studentId));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Event not found with id: " + eventId));

        // Check duplicate registration
        boolean alreadyRegistered =
                registrationRepository.existsByStudentIdAndEventId(
                        studentId,
                        eventId
                );

        if (alreadyRegistered) {
            throw new DuplicateRegistrationException(
                    "Student is already registered for this event"
            );
        }

        // Check event capacity
        long registeredCount =
                registrationRepository.countByEventIdAndStatus(
                        eventId,
                        RegistrationStatus.REGISTERED
                );

        if (registeredCount >= event.getMaxSeats()) {
            throw new EventFullException(
                    "Event is full. No more registrations are available."
            );
        }

        // Create registration
        Registration registration = new Registration();

        registration.setStudent(student);
        registration.setEvent(event);
        registration.setRegistrationDate(LocalDate.now());

        // IMPORTANT:
        // Use enum, NOT String
        registration.setStatus(RegistrationStatus.REGISTERED);

        return registrationRepository.save(registration);
    }

    // =========================
    // GET ALL REGISTRATIONS
    // =========================

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    // =========================
    // GET REGISTRATION BY ID
    // =========================

    public Registration getRegistrationById(Long id) {

        return registrationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Registration not found with id: " + id));
    }

    // =========================
    // GET REGISTRATIONS BY EVENT
    // =========================

    public List<Registration> getRegistrationsByEvent(Long eventId) {

        // Verify event exists
        eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Event not found with id: " + eventId));

        return registrationRepository.findByEventId(eventId);
    }

    // =========================
    // GET REGISTRATIONS BY STUDENT
    // =========================

    public List<Registration> getRegistrationsByStudent(Long studentId) {

        // Verify student exists
        studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + studentId));

        return registrationRepository.findByStudentId(studentId);
    }

    // =========================
    // CANCEL REGISTRATION
    // =========================

    public Registration cancelRegistration(Long id) {

        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Registration not found with id: " + id));

        registration.setStatus(RegistrationStatus.CANCELLED);

        return registrationRepository.save(registration);
    }

    // =========================
    // DELETE REGISTRATION
    // =========================

    public void deleteRegistration(Long id) {

        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Registration not found with id: " + id));

        registrationRepository.delete(registration);
    }

    // =========================
    // DELETE ALL REGISTRATIONS
    // FOR AN EVENT
    // =========================

    public void deleteRegistrationsByEventId(Long eventId) {

        List<Registration> registrations =
                registrationRepository.findByEventId(eventId);

        if (!registrations.isEmpty()) {
            registrationRepository.deleteAll(registrations);
        }
    }
}