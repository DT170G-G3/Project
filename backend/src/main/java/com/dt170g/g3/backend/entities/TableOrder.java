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
 *   - note      : Optional note
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
import java.util.ArrayList;
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
                        "LEFT JOIN FETCH o.table " +
                        "WHERE o.table.id = :sittingId"
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

    @Column(name = "note", length = 255)
    private String note;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private RestaurantTable table;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderCarteDish> dishes = new ArrayList<>();
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDrink> drinks = new ArrayList<>();



    // =====================
    // Constructors
    // =====================

    public TableOrder() {
    }

    public TableOrder(Integer orderNo, RestaurantTable table) {
        this.orderNo = orderNo;
        this.table = table;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public RestaurantTable getTable() {
        return table;
    }

    public void setTable(RestaurantTable table) {
        this.table = table;
    }

    public List<OrderCarteDish> getDishes() { return dishes; }
    public void setDishes(List<OrderCarteDish> dishes) { this.dishes = dishes; }

    public List<OrderDrink> getDrinks() { return drinks; }
    public void setDrinks(List<OrderDrink> drinks) { this.drinks = drinks; }
}