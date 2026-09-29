package com.example.College.Event.Registration.System.repository;

import com.example.College.Event.Registration.System.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}