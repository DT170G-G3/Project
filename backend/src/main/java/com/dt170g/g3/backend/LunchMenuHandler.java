package com.dt170g.g3.backend;

import com.dt170g.g3.backend.entities.Dish;
import com.dt170g.g3.backend.entities.LunchMenu;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
@Named("lunch")
public class LunchMenuHandler{
    @PersistenceContext
    EntityManager entityManager;

    public List<Dish> getDishesToday() {

        List<LunchMenu> dishIDs = entityManager.createQuery(
                "SELECT menu from LunchMenu menu WHERE menu.date = :today",
                LunchMenu.class)
                .setParameter("today", LocalDate.now())
                .getResultList();

        if (dishIDs.isEmpty()) {
             return Collections.emptyList();
        } else {
            return dishIDs.get(0).getDishes();
        }
    }

    public List<List<Dish>> getDishesWeek(){
        return null;
    }

    @Transactional
    public void uploadDish(Dish dish) {
        entityManager.persist(dish);
    }

}
