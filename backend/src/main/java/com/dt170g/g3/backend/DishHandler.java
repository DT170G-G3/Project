/*
 * Java Application project.
 * Author: Jesper
 */
package com.dt170g.g3.backend;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import com.dt170g.g3.backend.entities.LunchDish;
import jakarta.transaction.Transactional;

import java.util.List;
/*
 * Class exposed to the JFA, also known as a "bean".
 * These functions can be reaches by the facelet webapplication.
 * It is reached via the given name "dish".
 *
 * Example:
 * If you use "#{dish.dishes}", you will call the getDishes function inside dish!
 * You always skip the "get" or "set", for some reason.
 * Since this returns a list you can loop through via ui:repeat.
 *
 */
@ApplicationScoped
@Named("dish")
public class DishHandler {
    @PersistenceContext
    EntityManager entityManager;

    private LunchDish newDish = new LunchDish();

    public LunchDish getDishById(int id){
        LunchDish dish = entityManager.find(LunchDish.class, id);
        return dish;
    }
    public List<LunchDish> getDishes(){
        TypedQuery<LunchDish> messageQuery = entityManager.createNamedQuery("Dish.getAll", LunchDish.class);
        List<LunchDish> resultList = messageQuery.getResultList();
        return resultList;
    }

    public LunchDish getNewDish(){
        return newDish;
    }

@Transactional
    public void addDish(){
        entityManager.persist(newDish);
        this.newDish = new LunchDish();
    }



}

