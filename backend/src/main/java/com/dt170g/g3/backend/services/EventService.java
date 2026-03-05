package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.entities.Event;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;


@ApplicationScoped
public class EventService {
    @PersistenceContext
    EntityManager entityManager;

    public List<Event> findAllWithPostsAndComments(){
        return entityManager.createNamedQuery("Event.findAllWithPostsAndComments", Event.class)
                .getResultList();
    }
}
