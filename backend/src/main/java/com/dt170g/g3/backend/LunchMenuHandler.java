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
import java.util.ArrayList;

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

    private String selectedDate; //LocalDate?
    private List<Integer> selectedDishIds = new ArrayList<>();

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

    /**
     * Checks if a LunchMenu already exists for the given date.
     *
     * @param date ;The date to check
     * @return true if a menu exists, false otherwise
     */
    public boolean menuExistsForDate(LocalDate date) {
        List<LunchMenu> existing = entityManager.createQuery(
                        "SELECT m FROM LunchMenu m WHERE m.date = :date", LunchMenu.class)
                .setParameter("date", date)
                .getResultList();

        return !existing.isEmpty();
    }

    /**
     * Creates and persists a new lunch menu for a given date with selected dishes.
     *
     * Throws:
     * - IllegalArgumentException if date is missing or no dishes are selected.
     * - IllegalStateException if a menu already exists for the given date.
     *
     * Note: The method is transactional; any exceptions will cause the transaction to roll back.
     */
    @Transactional
    public void addLunch() {
        if (selectedDate == null || selectedDate.isEmpty()) {
            throw new IllegalArgumentException("Date must be provided to create a lunch menu.");
        }

        if (selectedDishIds.isEmpty()) {
            throw new IllegalArgumentException("At least one dish must be selected for the menu.");
        }

        LocalDate menuDate = LocalDate.parse(selectedDate);
        if (menuExistsForDate(menuDate)) {
            throw new IllegalStateException("A lunch menu already exists for the selected date.");
        }


        LunchMenu menu = new LunchMenu();
        menu.setDate(menuDate);  // Uses default yyyy-MM-dd format


        List<Dish> dishes = entityManager.createQuery(
                        "SELECT d FROM Dish d WHERE d.id IN :ids", Dish.class)
                .setParameter("ids", selectedDishIds)
                .getResultList();

        menu.setDishes(dishes);
        entityManager.persist(menu);
        selectedDate = null;
        selectedDishIds.clear();
    }

    // Getters and setters for JSF binding
    public List<Integer> getSelectedDishIds() { return selectedDishIds; }
    public void setSelectedDishIds(List<Integer> selectedDishIds) { this.selectedDishIds = selectedDishIds; }

    public String getSelectedDate() {
        return selectedDate;
    }
    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }
}
