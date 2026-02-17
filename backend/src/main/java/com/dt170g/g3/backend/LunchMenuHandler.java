package com.dt170g.g3.backend;

import com.dt170g.g3.backend.entities.Dish;
import com.dt170g.g3.backend.entities.LunchMenu;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.List;

/*
 * Class exposed to the JFA, also known as a "bean".
 * These functions can be reaches by the facelet webapplication.
 * It is reached via the given name "lunch".
 */
@ApplicationScoped
@Named("lunch")
public class LunchMenuHandler{
    @PersistenceContext
    EntityManager entityManager;

    /*Creates a query for the database, and returns all the dishes for the current day*/
    public List<Dish> getLunchDishesToday() {
        List<LunchMenu> dishIDs = entityManager.createQuery(
                "SELECT menu FROM LunchMenu menu WHERE menu.date = :today",
                LunchMenu.class)
                .setParameter("today", LocalDate.now())
                .getResultList();

        if (dishIDs.isEmpty()) {
             return Collections.emptyList(); //Needed in case there was no food.
        } else {
            return dishIDs.get(0).getDishes();
        }
    }

    /*Creates a query for the database, and returns all the menues for the current week*/
    public List<LunchMenu> getMenuWeek(){
        LocalDate monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate saturday = monday.plusDays(5);
        List<LunchMenu> weekMenu = entityManager.createQuery(
                "SELECT menu FROM LunchMenu menu " +
                   "WHERE menu.date BETWEEN :start AND :end " +
                   "ORDER BY menu.date",
                    LunchMenu.class
                )
                .setParameter("start",monday)
                .setParameter("end", saturday)
                .getResultList();

        return weekMenu;
    }

    @Transactional
    public void addLunch() {//List<Integer> dishIDs, LocalDate date) {

    }

}
