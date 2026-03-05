/**
 * OrderDrink.java
 *
 * JPA entity representing a drink item within a table order.
 * Maps the relationship between a TableOrder and a Drink,
 * including the quantity ordered.
 *
 * Database mapping:
 *   - id       : Primary key
 *   - order    : Many-to-one relationship to TableOrder
 *   - drink    : Many-to-one relationship to Drink
 *   - quantity : Number of portions ordered
 *
 * Ensures uniqueness of table_order_id and drink_id per entry.
 *
 * Author: Axel Friman
 * Date: 2026-03-05
 */
package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;
import jakarta.json.bind.annotation.JsonbTransient;

@Entity
@Table(name = "table_order_drink", uniqueConstraints = @UniqueConstraint(columnNames = {"table_order_id", "drink_id"}))
public class OrderDrink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "table_order_id", nullable = false)
    @JsonbTransient
    private TableOrder order;

    @ManyToOne
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    @Column(nullable = false)
    private int quantity;

    // ----- getters & setters -----

    public int getId() {
        return id;
    }

    public TableOrder getOrder() {
        return order;
    }

    public void setOrder(TableOrder order) {
        this.order = order;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

