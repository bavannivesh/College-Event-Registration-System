package com.example.College.Event.Registration.System.controller;

import com.example.College.Event.Registration.System.dto.RegistrationRequest;
import com.example.College.Event.Registration.System.entity.Registration;
import com.example.College.Event.Registration.System.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registrations")
@CrossOrigin(origins = "*")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    // Register a student for an event
    @PostMapping("/student/{studentId}")
    public ResponseEntity<Registration> registerStudent(
            @PathVariable Long studentId,
            @RequestBody RegistrationRequest request) {

        Registration registration = registrationService.registerStudent(
                studentId,
                request.getEventId()
        );

        return ResponseEntity.ok(registration);
    }

    // Get all registrations
    @GetMapping
    public ResponseEntity<?> getAllRegistrations() {
        return ResponseEntity.ok(
                registrationService.getAllRegistrations()
        );
    }

    // Get registration by ID
    @GetMapping("/{id}")
    public ResponseEntity<Registration> getRegistrationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                registrationService.getRegistrationById(id)
        );
    }
}
