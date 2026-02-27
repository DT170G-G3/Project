/**
 * DrinkService.java
 *
 * Service class for managing Drink entities.
 * Provides methods to:
 *   - getDrinkById(int id)   : Retrieve a single drink by its ID
 *   - getDrinkByName(String) : Retrieve a single drink by its unique name
 *   - findAllDrinks()        : Retrieve all drinks from the database
 *
 * Uses JPA EntityManager to perform database operations.
 * Methods are application-scoped and can be injected into REST API resources.
 *
 * Example usage:
 *   drinkService.findAllDrinks();
 *   drinkService.getDrinkById(1);
 *   drinkService.getDrinkByName("CocaCola");
 *
 * Author: Axel Friman
 * Date: 2026-02-20
 */
package com.dt170g.g3.backend.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import com.dt170g.g3.backend.entities.Drink;
import jakarta.transaction.Transactional;
import java.util.List;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class DrinkService {
    @PersistenceContext
    EntityManager entityManager;

    public Drink getDrinkById(int id){
        return entityManager.find(Drink.class, id);
    }
    public Drink getDrinkByName(String name){
        try {
            TypedQuery<Drink> messageQuery = entityManager.createNamedQuery("Drink.findByName", Drink.class);
            messageQuery.setParameter("name", name);
            return messageQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Drink> findAllDrinks(){
        TypedQuery<Drink> messageQuery = entityManager.createNamedQuery("Drink.getAll", Drink.class);
        return messageQuery.getResultList();
    }
}