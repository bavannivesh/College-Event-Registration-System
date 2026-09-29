package com.example.College.Event.Registration.System.service;

import com.example.College.Event.Registration.System.entity.Event;
import com.example.College.Event.Registration.System.repository.EventRepository;
import com.example.College.Event.Registration.System.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;

    public EventService(
            EventRepository eventRepository,
            RegistrationRepository registrationRepository) {

        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
    }

    // =========================
    // CREATE EVENT
    // =========================

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // =========================
    // GET ALL EVENTS
    // =========================

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // =========================
    // GET EVENT BY ID
    // =========================

    public Event getEventById(Long id) {

        return eventRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Event not found with id: " + id
                        ));
    }

    // =========================
    // UPDATE EVENT
    // =========================

    public Event updateEvent(Long id, Event eventDetails) {

        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Event not found with id: " + id
                        ));

        /*
         * Copy only fields that are already supported
         * by the Event entity.
         *
         * We intentionally do NOT call getName()
         * or getTime() because those methods do not
         * exist in your current Event entity.
         */

        existingEvent.setDate(eventDetails.getDate());
        existingEvent.setDescription(eventDetails.getDescription());
        existingEvent.setVenue(eventDetails.getVenue());
        existingEvent.setMaxSeats(eventDetails.getMaxSeats());

        return eventRepository.save(existingEvent);
    }

    // =========================
    // DELETE EVENT
    // =========================

    public void deleteEvent(Long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Event not found with id: " + id
                        ));

        /*
         * Delete registrations first because
         * registrations contain a foreign key
         * referring to the event.
         */
        registrationRepository.deleteByEventId(id);

        eventRepository.delete(event);
    }
}