/**
 * Drink.java
 *
 * JPA entity representing a drink in the restaurant system.
 * Maps to the "drink" table in the database.
 *
 * NamedQueries provided:
 *   - Drink.getAll       : Retrieve all drinks
 *   - Drink.findByName   : Retrieve a drink by its name
 *
 * Fields:
 *   - id      : Auto-generated primary key
 *   - name    : Unique name of the drink
 *   - price   : Price of the drink
 *
 * Author: Axel Friman
 * Date: 2026-02-20
 */
package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;


@NamedQueries({
        @NamedQuery(
                name = "Drink.getAll",
                query = "SELECT d FROM Drink d"
        ),
        @NamedQuery(
                name = "Drink.findByName",
                query = "SELECT d FROM Drink d WHERE d.name = :name"
        )
})
@Entity
@Table(name = "drink")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private double price;

    // =====================
    // Constructors
    // =====================

    public Drink() {}

    public Drink(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // =====================
    // Getters & Setters
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}