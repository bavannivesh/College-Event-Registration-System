package com.example.College.Event.Registration.System.controller;

import com.example.College.Event.Registration.System.entity.Organizer;
import com.example.College.Event.Registration.System.service.OrganizerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizers")
public class OrganizerController {

    private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @PostMapping
    public ResponseEntity<Organizer> createOrganizer(
            @Valid @RequestBody Organizer organizer) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(organizerService.createOrganizer(organizer));
    }

    @GetMapping
    public ResponseEntity<List<Organizer>> getAllOrganizers() {
        return ResponseEntity.ok(
                organizerService.getAllOrganizers()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizer> getOrganizer(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                organizerService.getOrganizerById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organizer> updateOrganizer(
            @PathVariable Long id,
            @Valid @RequestBody Organizer organizer) {

        return ResponseEntity.ok(
                organizerService.updateOrganizer(id, organizer)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrganizer(
            @PathVariable Long id) {

        organizerService.deleteOrganizer(id);

        return ResponseEntity.ok(
                "Organizer deleted successfully"
        );
    }
}