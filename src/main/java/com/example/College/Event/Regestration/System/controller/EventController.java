package com.example.College.Event.Registration.System.controller;

import com.example.College.Event.Registration.System.entity.Event;
import com.example.College.Event.Registration.System.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // CREATE EVENT
    @PostMapping
    public ResponseEntity<Event> createEvent(
            @RequestBody Event event) {

        Event savedEvent = eventService.createEvent(event);

        return new ResponseEntity<>(
                savedEvent,
                HttpStatus.CREATED
        );
    }

    // GET ALL EVENTS
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {

        return ResponseEntity.ok(
                eventService.getAllEvents()
        );
    }

    // GET EVENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                eventService.getEventById(id)
        );
    }

    // UPDATE EVENT
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id,
            @RequestBody Event event) {

        return ResponseEntity.ok(
                eventService.updateEvent(id, event)
        );
    }

    // DELETE EVENT
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(
            @PathVariable Long id) {

        eventService.deleteEvent(id);

        return ResponseEntity.ok(
                "Event deleted successfully"
        );
    }
}