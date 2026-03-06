package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.entities.Event;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;


@ApplicationScoped
public class EventService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Event> findAllWithPostsAndComments(){
        return entityManager.createNamedQuery("Event.findAllWithPostsAndComments", Event.class)
                .getResultList();
    }

    public List<Event> findAll(){
        return entityManager.createNamedQuery("Event.findAll", Event.class)
                .getResultList();
    }

    public Event getEventById(int id){
        return entityManager.find(Event.class, id);
    }


    @Transactional
    public void createEvent(Event event){
        entityManager.persist(event);
    }

}
