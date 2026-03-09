package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.entities.Comment;
import com.dt170g.g3.backend.entities.Event;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;


@ApplicationScoped
public class EventService {
    @PersistenceContext
    EntityManager entityManager;

    public List<Event> findAllWithPostsAndComments(){
        return entityManager.createNamedQuery("Event.findAllWithPostsAndComments", Event.class)
                .getResultList();
    }

    @Transactional
    public void saveComment(Comment comment) {
        Event managedEvent = entityManager.merge(comment.getEvent());
        comment.setEvent(managedEvent);
        //uppdatera cach
        managedEvent.getComments().add(comment);
        //spra i db
        entityManager.persist(comment);
    }
}
