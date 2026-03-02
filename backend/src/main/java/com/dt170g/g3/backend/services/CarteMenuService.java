/**
 * CarteMenuService.java
 *
 * Service class for managing CarteMenu entities in the restaurant application.
 * Provides methods to:
 *  - getAllMenus() : Retrieve all menus with their associated dishes
 *  - findByCategory(int menuID, int categoryID)    : Retrieve all dishes on a specific menu filtered by category
 *  - findById(int id)  : Retrieve a single menu by its ID
 *
 * Uses JPA EntityManager to perform database operations.
 * Methods are application-scoped and can be injected into REST API resources.
 *
 * Example usage:
 *   List<CarteMenu> menus = carteMenuService.getAllMenus();
 *   List<CarteDish> dishes = carteMenuService.findByCategory(1, 2);
 *   CarteMenu menu = carteMenuService.findById(1);
 *
 * Author: Axel Friman
 * Date: 2026-02-26
 */
package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.beans.CarteMenuBean;
import com.dt170g.g3.backend.entities.CarteMenu;
import com.dt170g.g3.backend.entities.CarteDish;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CarteMenuService {
    @PersistenceContext
    EntityManager entityManager;

    public List <CarteMenu> getAllMenus(){
        TypedQuery<CarteMenu> messageQuery = entityManager.createNamedQuery("CarteMenu.getAll", CarteMenu.class);
        return messageQuery.getResultList();
    }
    public List<CarteDish> findByCategory(int menuID, int categoryID) {
        return entityManager
                .createNamedQuery("CarteMenu.findDishesByCategoryId", CarteDish.class)
                .setParameter("menuId", menuID)
                .setParameter("categoryId", categoryID)
                .getResultList();
    }
    public CarteMenu findById(int id) {
        return entityManager
                .createNamedQuery("CarteMenu.findById", CarteMenu.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<CarteDish> getMenuDishes() {
        System.out.println("--------------------------------jhjhih");
        return entityManager
                .createNamedQuery("CarteMenu.getDishesOnMenu", CarteDish.class)
                .setParameter("menuId", 1)
                .getResultList();
    }


    @Transactional
    public void addDishToMenu(CarteDish dish){
        CarteMenu menu = entityManager.find(CarteMenu.class, 1);
        menu.getDishes().add(dish);
        System.out.println("Adding dish to menu");
    }

    @Transactional
    public void removeDishfromMenu(int id){
        CarteMenu menu = entityManager.find(CarteMenu.class, 1);
        CarteDish dish = entityManager.find(CarteDish.class, id);
        menu.getDishes().remove(dish);

    }
}