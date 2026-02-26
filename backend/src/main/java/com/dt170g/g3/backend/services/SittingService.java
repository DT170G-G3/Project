/**
 * SittingService.java
 *
 * Service class for managing Sitting entities.
 * Provides methods to:
 *   - getSittingById(int id)        : Retrieve a single sitting by its ID
 *   - findAllSittings()             : Retrieve all sittings from the database
 *   - findSittingsByDate(LocalDate) : Retrieve sittings for a specific date
 *   - findSittingsByTable(int id)   : Retrieve sittings for a specific table
 *
 * Uses JPA EntityManager to perform database operations.
 * Methods are application-scoped and can be injected into REST API resources.
 *
 * Example usage:
 *   sittingService.findAllSittings();
 *   sittingService.getSittingById(1);
 *   sittingService.findSittingsByDate(LocalDate.now());
 *   sittingService.findSittingsByTable(3);
 *
 * Author: Axel Friman
 * Date: 2026-02-26
 */
package com.dt170g.g3.backend.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import com.dt170g.g3.backend.entities.Sitting;
import jakarta.transaction.Transactional;
import java.util.List;
import java.time.LocalDate;

@ApplicationScoped
public class SittingService {
    @PersistenceContext
    EntityManager entityManager;

    public Sitting getSittingById(int id){
        return entityManager.find(Sitting.class, id);
    }
    public List<Sitting> findAllSittings(){
        TypedQuery<Sitting> messageQuery = entityManager.createNamedQuery("Sitting.getAll", Sitting.class);
        return messageQuery.getResultList();
    }
    public List<Sitting> findSittingsByDate(LocalDate date){
        TypedQuery<Sitting> messageQuery = entityManager.createNamedQuery("Sitting.findByDate", Sitting.class);
        messageQuery.setParameter("date", date);
        return messageQuery.getResultList();
    }

    public List<Sitting> findSittingsByTable(int id){
        TypedQuery<Sitting> messageQuery = entityManager.createNamedQuery("Sitting.findByTable", Sitting.class);
        messageQuery.setParameter("tableId", id);
        return messageQuery.getResultList();
    }
}