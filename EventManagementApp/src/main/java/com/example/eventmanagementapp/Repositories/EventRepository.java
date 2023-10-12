package com.example.eventmanagementapp.Repositories;

import com.example.eventmanagementapp.Domain.Event;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EventRepository {
    private final EntityManagerFactory entityManagerFactory;

    public EventRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    public void save(Event event) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(event);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Event findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Event event = entityManager.find(Event.class, id);
        entityManager.close();
        return event;
    }

    public List<Event> getAllEvents() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Event> events = entityManager.createQuery("SELECT u FROM Event u", Event.class).getResultList();
        entityManager.close();
        return events;
    }

    public void update(Long eventId, Event updatedEvent) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Event event = entityManager.find(Event.class, eventId);
        if (event != null) {
            event.setName(updatedEvent.getName());
            event.setDescription(updatedEvent.getDescription());
            entityManager.merge(event);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Long eventId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Event event = entityManager.find(Event.class, eventId);
        if (event != null) {
            entityManager.remove(event);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}