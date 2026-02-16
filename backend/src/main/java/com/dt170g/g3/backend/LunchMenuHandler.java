package com.dt170g.g3.backend;

import com.dt170g.g3.backend.entities.Dish;
import com.dt170g.g3.backend.entities.LunchMenu;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
@Named("lunch")
public class LunchMenuHandler{
    @PersistenceContext
    EntityManager entityManager;

    public List<Dish> getLunchDishesToday() {

        List<LunchMenu> dishIDs = entityManager.createQuery(
                "SELECT menu FROM LunchMenu menu WHERE menu.date = :today",
                LunchMenu.class)
                .setParameter("today", LocalDate.now())
                .getResultList();

        if (dishIDs.isEmpty()) {
             return Collections.emptyList();
        } else {
            return dishIDs.get(0).getDishes();
        }
    }

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
}
