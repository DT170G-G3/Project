/**
 * CarteDishService.java
 *
 * Service class for managing dishes in the restaurant application.
 * Provides methods to:
 *  - getDishById(int)              : Retrieve a specific dish by its ID
 *  - getDishesByCategory(int id)   : Retrieve all dishes by category
 *  - findAllDishes()               : Retrieve all dishes from the database
 *
 * Uses JPA EntityManager to access the CarteDish entities.
 * This class is an application-scoped CDI bean.
 *
 * Example usage:
 *   CarteDish dish = dishService.getDishById(1);
 *   List<CarteDish> allDishes = dishService.findAllDishes();
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
import com.dt170g.g3.backend.entities.CarteDish;
import com.dt170g.g3.backend.entities.CarteMenu;
import jakarta.transaction.Transactional;

import java.util.List;
@ApplicationScoped
public class CarteDishService {
    @PersistenceContext
    EntityManager entityManager;

    public CarteDish getDishById(int id){
        return entityManager.find(CarteDish.class, id);
    }
    public List<CarteDish> getDishesByCategory(int id){

        TypedQuery<CarteDish> messageQuery = entityManager.createNamedQuery("CarteDish.findByCategory", CarteDish.class);
        messageQuery.setParameter("id", id);
        return messageQuery.getResultList();

    }
    public List<CarteDish> findAllDishes(){
        TypedQuery<CarteDish> messageQuery = entityManager.createNamedQuery("CarteDish.getAll", CarteDish.class);
        return messageQuery.getResultList();
    }

    /**
     *
     * @return all dishes on the menu. Ordered by starter, main, dessert.
     */
    public List<CarteDish> getMenuDishes() {
        TypedQuery<CarteDish> messageQuery = entityManager.createNamedQuery("CarteDish.getDishesFromMenuByCategory", CarteDish.class);
        return messageQuery.getResultList();
    }


    @Transactional
    public void saveToDatabase(CarteDish dish){
        entityManager.persist(dish);
    }

}