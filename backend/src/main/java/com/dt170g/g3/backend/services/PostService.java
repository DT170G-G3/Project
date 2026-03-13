package com.dt170g.g3.backend.services;


import com.dt170g.g3.backend.entities.Event;
import com.dt170g.g3.backend.entities.Post;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PostService {
    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public void createPost(Post post) {
        Event managedEvent = entityManager.merge(post.getEvent());
        post.setEvent(managedEvent);

        entityManager.persist(post);

        managedEvent.getPosts().add(post);
    }

}





