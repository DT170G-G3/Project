/*
 * Java Application project.
 * Author: Jesper
 */
package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.entities.LunchMenu;
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
public class LunchDishService {
    @PersistenceContext
    EntityManager entityManager;

    public LunchDish getDishById(int id){
        return entityManager.find(LunchDish.class, id);
    }
    public List<LunchDish> findAllLunchDishes(){
        TypedQuery<LunchDish> messageQuery = entityManager.createNamedQuery("LunchDish.getAll", LunchDish.class);
        return messageQuery.getResultList();
    }

    @Transactional
    public void saveDish(LunchDish dish){
        entityManager.persist(dish);
    }

    /**
     * Updates the description of a specific Dish.
     *
     * The method retrieves the Dish entity by its id and updates its
     * description field if the entity exists. The change is automatically
     * persisted to the database within the transactional context.
     *
     * @param dishId the id of the Dish to update
     * @param description the new description to assign to the Dish
     */
    @Transactional
    public void editDish(int dishId, String description){
        LunchDish dish = entityManager.find(LunchDish.class, dishId);
        if(dish != null){
            dish.setDescription(description);
        }
    }

    public LunchDish findExistingDish(LunchDish dish){
        List<LunchDish> existing = entityManager.createQuery(
                            "SELECT m FROM LunchDish m WHERE lower(m.name) = lower(:name) AND " +
                                "lower(m.description) = lower(:description) AND " +
                                "m.price = :price", LunchDish.class)
                .setParameter("name", dish.getName())
                .setParameter("description", dish.getDescription())
                .setParameter("price", dish.getPrice())
                .getResultList();
        if (existing.isEmpty()){
            return null;
        }
        return existing.get(0);
    }
}