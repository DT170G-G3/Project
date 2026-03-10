package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.entities.Comment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CommentService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createComment(Comment comment){
        entityManager.persist(comment);
    }

    public List<Comment> findAll() {
        return entityManager.createQuery(
                "SELECT c FROM Comment c ORDER BY c.dateAndTime DESC",
                Comment.class
        ).getResultList();
    }

    public List<Comment> findByEventId(int eventId) {
        return entityManager.createQuery(
                        "SELECT c FROM Comment c WHERE c.event.id = :eventId ORDER BY c.dateAndTime ASC",
                        Comment.class
                )
                .setParameter("eventId", eventId)
                .getResultList();
    }
}
