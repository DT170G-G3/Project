/**
 * Category.java
 *
 * JPA entity representing a category of dishes in the restaurant application.
 * Maps to the "category" table in the database.
 * Provides fields for:
 *  - id   : Primary key, auto-incremented
 *  - name : Name of the category (unique, not null)
 *
 * Also maintains a one-to-many relationship with CarteDish entities.
 *
 * Example usage:
 *   Category category = new Category("Starters");
 *   List<CarteDish> dishes = category.getDishes();
 *
 * Author: Axel Friman
 * Date: 2026-02-20
 */
package com.dt170g.g3.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

    // Constructors
    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}