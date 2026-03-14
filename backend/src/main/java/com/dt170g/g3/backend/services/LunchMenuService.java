package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.beans.LunchDishBean;
import com.dt170g.g3.backend.entities.LunchDish;
import com.dt170g.g3.backend.entities.LunchMenu;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
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
    @Inject
    private LunchDishBean dishBean;
    @Inject
    private LunchDishService lunchDishService;

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
        LocalDate friday = monday.plusDays(4);
        List<LunchMenu> weekMenu = entityManager.createNamedQuery(
             "Lunch.getWeeklyMenues",LunchMenu.class)
                .setParameter("start",monday)
                .setParameter("end", friday)
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
    public void removeDishFromMenu(String localDate, LunchDish dish) {
        LocalDate date = LocalDate.parse(localDate);

        LunchMenu menu = getLunchMenuByDate(date);
        LunchMenu managedMenu = entityManager.find(LunchMenu.class, menu.getId());
        LunchDish managedDish = entityManager.find(LunchDish.class, dish.getId());

        if (managedMenu != null && managedDish != null) {
            managedMenu.getDishes().remove(managedDish);  // tar bort raden i dish_lunch_menu
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
    @Transactional
    public LunchMenu getLunchMenuByDate(LocalDate date) {
        List<LunchMenu> result = entityManager.createNamedQuery("Lunch.getLunchByDate", LunchMenu.class)
                .setParameter("targetDate",date)
                .getResultList();
        if (result.isEmpty()){
            LunchMenu menu = new LunchMenu(date);
            entityManager.persist(menu);
            return menu;
        }
        return result.get(0);
    }

    @Transactional
    public List<LunchDish> getLunchDishesByDate(LocalDate date){
        if (!menuExistsForDate(date)) {
            return Collections.emptyList();
        }
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

    @Transactional
    public void saveDishToLunchMenu(String localDate){
        LocalDate date = LocalDate.parse(localDate);
        LunchDish dish = dishBean.getNewDish();

        // Hämta/skapa meny om den inte finns
        LunchMenu menu;
        if (!menuExistsForDate(date)) {
            menu = new LunchMenu();
            menu.setDate(date);
            entityManager.persist(menu);
        } else {
            menu = getLunchMenuByDate(date);
        }

        // Sök efter rätten
        LunchDish dishToLink;
        List<LunchDish> existing = entityManager.createQuery(
                        "SELECT d FROM LunchDish d WHERE d.name = :name", LunchDish.class)
                .setParameter("name", dish.getName())
                .getResultList();

        // Har listan element behandlas första elementet som en tidigare skapad rätt.
        if (!existing.isEmpty()) {
            LunchDish dbDish = existing.get(0);
            //Update existing dish, else we can't modify dishes.
            dbDish.setDescription(dish.getDescription());
            dbDish.setPrice(dish.getPrice());

            dishToLink = entityManager.merge(dbDish);
        } else {
            ///dishToLink = entityManager.merge(dish);
            entityManager.persist(dish);
            dishToLink = dish;
        }

        // Koppla ihop och lägg rätt i menyn
        if (!menu.getDishes().contains(dishToLink)) {
            menu.getDishes().add(dishToLink);
            entityManager.merge(menu); // Uppdatera kopplingen
        }
        dishBean.reset(); //Empties the form on the website
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
