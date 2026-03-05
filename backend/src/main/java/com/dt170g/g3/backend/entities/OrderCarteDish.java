/**
 * OrderCarteDish.java
 *
 * JPA entity representing a dish item within a table order.
 * Maps the relationship between a TableOrder and a CarteDish,
 * including the quantity ordered.
 *
 * Database mapping:
 *   - id            : Primary key
 *   - order         : Many-to-one relationship to TableOrder
 *   - carteDish     : Many-to-one relationship to CarteDish
 *   - quantity      : Number of portions ordered
 *
 * Ensures uniqueness of table_order_id and carte_dish_id per entry.
 *
 * Author: Axel Friman, Jesper Elovsson
 * Date: 2026-03-05
 */
package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;
import jakarta.json.bind.annotation.JsonbTransient;

@Entity
@Table(name = "table_order_carte_dish",
        uniqueConstraints = @UniqueConstraint(columnNames = {"table_order_id", "carte_dish_id"}))
public class OrderCarteDish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "table_order_id", nullable = false)
    @JsonbTransient
    private TableOrder order;

    @ManyToOne
    @JoinColumn(name = "carte_dish_id", nullable = false)
    private CarteDish carteDish;

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

    public CarteDish getCarteDish() {
        return carteDish;
    }

    public void setCarteDish(CarteDish carteDish) {
        this.carteDish = carteDish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}