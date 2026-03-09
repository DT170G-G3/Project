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
    private EntityManager entityManager;

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

    public List<Event> findAll(){
        return entityManager.createNamedQuery("Event.findAll", Event.class)
                .getResultList();
    }

    public Event getEventById(int id){
        return entityManager.find(Event.class, id);
    }

    @Transactional
    public void removeComment(int id){
        //Event event = entityManager.find(Event.class, id);
        Comment comment = entityManager.find(Comment.class, id);

        //entityManager.remove(event);

        entityManager.remove(comment);

    }

//    @Transactional
//    public void removeDishfromMenu(int id){
//        CarteMenu menu = entityManager.find(CarteMenu.class, 1);
//        CarteDish dish = entityManager.find(CarteDish.class, id);
//        menu.getDishes().remove(dish);
//
//
//    }


    @Transactional
    public void createEvent(Event event){
        entityManager.persist(event);
    }

}
