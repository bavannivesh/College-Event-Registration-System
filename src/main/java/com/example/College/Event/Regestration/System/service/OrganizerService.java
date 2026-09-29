package com.example.College.Event.Registration.System.service;

import com.example.College.Event.Registration.System.entity.Organizer;
import com.example.College.Event.Registration.System.exception.ResourceNotFoundException;
import com.example.College.Event.Registration.System.repository.OrganizerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerService {

    private final OrganizerRepository organizerRepository;

    public OrganizerService(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    public Organizer createOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    public Organizer getOrganizerById(Long id) {
        return organizerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Organizer not found with ID: " + id
                        ));
    }

    public Organizer updateOrganizer(Long id, Organizer organizer) {

        Organizer existing = getOrganizerById(id);

        existing.setName(organizer.getName());
        existing.setEmail(organizer.getEmail());
        existing.setDepartment(organizer.getDepartment());

        return organizerRepository.save(existing);
    }

    public void deleteOrganizer(Long id) {

        Organizer organizer = getOrganizerById(id);

        organizerRepository.delete(organizer);
    }
}