/**
 * Service class responsible for editing the description of the LunchMenu and remove dish from the menu.
 *
 *
 * Functionality includes:
 * - Updating information for a specific Dish (e.g. description).
 * - Removing a Dish from a specific LunchMenu without deleting the
 *   Dish entity from the database.
 *
 * Important:
 * The relationship between LunchMenu and Dish is handled through a
 * join table (dish_lunch_menu). When a dish is removed from a menu,
 * only the relationship entry is deleted — the Dish record itself
 * remains in the Dish table.
 *
 * Transactions:
 * Methods that modify persistent data are annotated with @Transactional
 * to ensure proper commit and rollback handling.
 *
 * Scope:
 * The service is ApplicationScoped, meaning one instance exists for the
 * lifetime of the application.
 *
 */


package com.dt170g.g3.backend;

import com.dt170g.g3.backend.entities.LunchDish;
import com.dt170g.g3.backend.entities.LunchMenu;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.time.LocalDate;

@Named("lunchMenuService")
@ApplicationScoped
public class LunchMenuService{

    @PersistenceContext
    private EntityManager entityManager;


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



}
