package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.entities.LunchDish;
import com.dt170g.g3.backend.entities.LunchMenu;
import jakarta.ejb.Local;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class LunchMenuService {
    @PersistenceContext
    EntityManager entityManager;

    /*Creates a query for the database, and returns all the dishes for the current day*/
    public List<LunchDish> getLunchDishesToday() {
        List<LunchMenu> dishIDs = entityManager.createNamedQuery(
                        "Lunch.getLunchDishesToday", LunchMenu.class)
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
        List<LunchMenu> weekMenu = entityManager.createNamedQuery(
             "Lunch.getWeeklyMenues",LunchMenu.class)
                .setParameter("start",monday)
                .setParameter("end", saturday)
                .getResultList();
        return weekMenu;
    }


    /**
     * Removes the association between a specific Dish and a LunchMenu.
     *
     * This method does not delete the Dish entity itself. It only removes
     * the relationship entry from the join table connecting the Dish
     * to the specified LunchMenu.
     *
     * @param dishId the id of the Dish to remove
     * @param menuId the id of the LunchMenu from which the Dish will be removed
     */
    @Transactional
    public void removeDishFromMenu(int dishId, int menuId) {

        LunchMenu menu = entityManager.find(LunchMenu.class, menuId);
        LunchDish dish = entityManager.find(LunchDish.class, dishId);

        if (menu != null && dish != null) {
            menu.getDishes().remove(dish);  // tar bort raden i dish_lunch_menu
        }
    }

    private String selectedDate; //LocalDate?
    private List<Integer> selectedDishIds = new ArrayList<>();

    /**
     * Retrieves all dishes for a specific date.
     *
     * The method queries the database for a LunchMenu with the given date
     * and returns its associated dishes.
     *
     * Assumes exactly one menu per date (date is UNIQUE in the database).
     *
     * Throws:
     *  - IllegalStateException if no menu exists for the date
     *  - IllegalStateException if multiple menus exist (data integrity issue)
     */
    public LunchMenu getLunchMenuByDate(LocalDate date) {
        try {
            return entityManager.createQuery(
                            "SELECT menu FROM LunchMenu menu WHERE menu.date = :targetDate",
                            LunchMenu.class)
                    .setParameter("targetDate", date)
                    .getSingleResult();

        } catch (NoResultException e) {
            throw new IllegalStateException("No lunch menu exists for date: " + date);
        } catch (NonUniqueResultException e) {
            throw new IllegalStateException("Multiple lunch menus exist for date: " + date);
        }
    }

    public List<LunchDish> getLunchDishesByDate(LocalDate date){
        LunchMenu menu = getLunchMenuByDate(date);
        return menu.getDishes();
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
    @Transactional
    public void createLunchMenu(LocalDate date){
        LunchMenu menu = new LunchMenu(date);
        entityManager.persist(menu);
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


        List<LunchDish> dishes = entityManager.createQuery(
                        "SELECT d FROM LunchDish d WHERE d.id IN :ids", LunchDish.class)
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
