/**
 * CarteMenu.java
 *
 * JPA entity representing a menu in the restaurant application.
 * Maps to the "carte_menu" table in the database.
 * Provides fields for:
 *  - id        : Primary key, auto-incremented
 *  - createdAt : Timestamp when the menu was created
 *  - dishes    : List of CarteDish entities associated with this menu
 *
 * Maintains a many-to-many relationship with CarteDish entities via the
 * "dish_carte_menu" join table.
 * Named queries included:
 *  - "CarteMenu.findById"             : Retrieve a menu by its ID
 *  - "CarteMenu.getAll"               : Retrieve all menus with their dishes
 *  - "CarteMenu.findDishesByCategoryId" : Retrieve all dishes on a menu for a specific category
 *
 * Example usage:
 *   CarteMenu menu = entityManager.find(CarteMenu.class, 1);
 *   List<CarteDish> dishes = menu.getDishes();
 *
 * Author: Axel Friman, Erik Hägglund
 * Date: 2026-02-26
 */
package com.dt170g.g3.backend.entities;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@NamedQueries({
        @NamedQuery(
                name = "CarteMenu.findById",
                query = "SELECT m FROM CarteMenu m WHERE m.id = :id"
        ),
        @NamedQuery(
                name = "CarteMenu.getAll",
                query = "SELECT DISTINCT m FROM CarteMenu m LEFT JOIN FETCH m.dishes"
        ),
        @NamedQuery(
                name = "CarteMenu.getDishesOnMenu",
                query =
                        "SELECT cd FROM CarteMenu m " +
                                "JOIN m.dishes cd " +
                                "JOIN cd.category c " +
                                "LEFT JOIN cd.foodType ft " +
                                "WHERE m.id = :menuId " +
                                "ORDER BY c.displayOrder ASC"
        ),
        @NamedQuery(
                name = "CarteMenu.findDishesByCategoryId",
                query = "SELECT d FROM CarteMenu m JOIN m.dishes d " +
                        "WHERE m.id = :menuId AND d.category.id = :categoryId"
        )
})
@Entity
@Table(name = "carte_menu")
public class CarteMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "dish_carte_menu",
            joinColumns = @JoinColumn(name = "carte_menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<CarteDish> dishes;



    // ----------------------
    // Constructors
    // ----------------------
    public CarteMenu() {

    }

    // ----------------------
    // Getters and Setters
    // ----------------------
    public int getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<CarteDish> getDishes() {
        return dishes;
    }

    public void setDishes(List<CarteDish> dishes) {
        this.dishes = dishes;
    }
}

