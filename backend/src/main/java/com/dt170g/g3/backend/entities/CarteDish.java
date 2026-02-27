/**
 * CarteDish.java
 *
 * JPA entity representing a dish in the restaurant application.
 * Maps to the "carte_dish" table in the database.
 * Provides fields for:
 *  - id          : Primary key, auto-incremented
 *  - name        : Name of the dish
 *  - description : Description of the dish
 *  - price       : Price of the dish
 *
 * Maintains a many-to-one relationship with Category entities.
 * Named queries included:
 *  - "CarteDish.getAll"            : Retrieve all dishes
 *  - "CarteDish.findByCategory"    : Retrieve all dishes by category
 *  - "CarteDish.findByName"        : Retrieve a dish by its name
 *
 * Example usage:
 *   CarteDish dish = new CarteDish("Pasta", "Tomato pasta", 12.50);
 *   Category category = dish.getCategory();
 *
 * Author: Axel Friman
 * Date: 2026-02-20
 */

package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "CarteDish.getAll",
                query = "SELECT cd FROM CarteDish cd JOIN FETCH cd.category"
        ),
        @NamedQuery(
                name = "CarteDish.findByCategory",
                query = "SELECT cd FROM CarteDish cd WHERE cd.category.name = :category"
        ),
        @NamedQuery(
                name = "CarteDish.findByName",
                query = "SELECT cd FROM CarteDish cd WHERE cd.name = :name"
        ),
        @NamedQuery(
                name = "CarteDish.getDishesFromMenuByCategory",
                query = "SELECT cd FROM CarteDish cd " +
                        "JOIN cd.carteMenu m " +
                        "JOIN cd.category c " +
                        "LEFT JOIN cd.typeGroup t " +
                        "WHERE m.id = 1 " +
                        "ORDER BY c.displayOrder ASC"
        )
})

@Entity
@Table(name = "carte_dish")
public class CarteDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private double price;




    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="carte_menu_id")
    private CarteMenu carteMenu;

    @ManyToOne
    @JoinColumn(name="type_of_id")
    private TypeOf typeGroup;



    // =====================
    // Constructors
    // =====================
    public CarteDish() {
        // No-arg constructor required by JPA
    }

    public CarteDish(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // =====================
    // Getters and Setters
    // =====================
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public TypeOf getTypeGroup() {
        return typeGroup;
    }


    public void setCategory(Category category) {
        this.category = category;
    }



}
