package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.entities.Comment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CommentService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createComment(Comment comment){
        entityManager.persist(comment);
    }
}
