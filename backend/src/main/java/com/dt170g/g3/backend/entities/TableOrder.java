/**
 * TableOrder.java
 *
 * JPA entity representing a table order in the restaurant system.
 * Maps to the "table_order" table in the database.
 *
 * NamedQueries provided:
 *   - TableOrder.getAll        : Retrieve all table orders
 *   - TableOrder.findBySitting : Retrieve all orders for a specific sitting, including associated dishes and drinks
 *
 * Fields:
 *   - id        : Auto-generated primary key
 *   - createdAt : Timestamp of when the order was created (database-generated)
 *   - orderNo   : Optional order number
 *   - sitting   : Reference to the Sitting this order belongs to
 *   - dishes    : List of CarteDish items in this order (many-to-many)
 *   - drinks    : List of Drink items in this order (many-to-many)
 *
 * Author: Axel Friman
 * Date: 2026-02-20
 */
package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;
import com.dt170g.g3.backend.entities.CarteDish;
import com.dt170g.g3.backend.entities.Drink;
import java.time.LocalDateTime;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "TableOrder.getAll",
                query = "SELECT o FROM TableOrder o"
        ),
        @NamedQuery(
                name = "TableOrder.findBySitting",
                query = "SELECT DISTINCT o FROM TableOrder o " +
                        "LEFT JOIN FETCH o.dishes " +
                        "LEFT JOIN FETCH o.drinks " +
                        "LEFT JOIN FETCH o.sitting " +
                        "WHERE o.sitting.id = :sittingId"
        )
})

@Entity
@Table(name = "table_order")
public class TableOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "order_no")
    private Integer orderNo;

    @ManyToOne
    @JoinColumn(name = "sitting_id", nullable = false)
    private Sitting sitting;

    @ManyToMany
    @JoinTable(
            name = "table_order_carte_dish",
            joinColumns = @JoinColumn(name = "table_order_id"),
            inverseJoinColumns = @JoinColumn(name = "carte_dish_id")
    )
    private List<CarteDish> dishes;

    @ManyToMany
    @JoinTable(
            name = "table_order_drink",
            joinColumns = @JoinColumn(name = "table_order_id"),
            inverseJoinColumns = @JoinColumn(name = "drink_id")
    )
    private List<Drink> drinks;

    // =====================
    // Constructors
    // =====================

    public TableOrder() {
    }

    public TableOrder(Integer orderNo, Sitting sitting) {
        this.orderNo = orderNo;
        this.sitting = sitting;
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


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Sitting getSitting() {
        return sitting;
    }

    public void setSitting(Sitting sitting) {
        this.sitting = sitting;
    }

    public List<CarteDish> getDishes() { return dishes; }
    public void setDishes(List<CarteDish> dishes) { this.dishes = dishes; }

    public List<Drink> getDrinks() { return drinks; }
    public void setDrinks(List<Drink> drinks) { this.drinks = drinks; }
}