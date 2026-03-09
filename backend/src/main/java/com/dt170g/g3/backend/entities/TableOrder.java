/**
 * TableOrder.java
 *
 * JPA entity representing a table order in the restaurant system.
 * Maps to the "table_order" table in the database.
 *
 * NamedQueries provided:
 *   - TableOrder.getAll        : Retrieve all table orders
 *   - TableOrder.findByTable : Retrieve all orders for a specific table, including associated dishes and drinks
 *
 * Fields:
 *   - id        : Auto-generated primary key
 *   - createdAt : Timestamp of when the order was created (database-generated)
 *   - orderNo   : Optional order number
 *   - note      : Optional note
 *   - table     : Reference to the table this order belongs to
 *   - dishes    : List of OrderCarteDish items in this order (many-to-many)
 *   - drinks    : List of OrderDrink items in this order (many-to-many)
 *
 * Author: Axel Friman
 * Date: 2026-03-05
 */
package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;
import com.dt170g.g3.backend.entities.CarteDish;
import com.dt170g.g3.backend.entities.Drink;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.ZoneId;
import jakarta.persistence.PrePersist;

@NamedQueries({
        @NamedQuery(
                name = "TableOrder.getAll",
                query = "SELECT DISTINCT o FROM TableOrder o " +
                        "LEFT JOIN FETCH o.table " +
                        "LEFT JOIN FETCH o.dishes d " +
                        "LEFT JOIN FETCH d.carteDish " +
                        "LEFT JOIN FETCH o.drinks dr " +
                        "LEFT JOIN FETCH dr.drink"
        ),
        @NamedQuery(
                name = "TableOrder.findByTable",
                query = "SELECT DISTINCT o FROM TableOrder o " +
                        "LEFT JOIN FETCH o.table " +
                        "LEFT JOIN FETCH o.dishes d " +
                        "LEFT JOIN FETCH d.carteDish " +
                        "LEFT JOIN FETCH o.drinks dr " +
                        "LEFT JOIN FETCH dr.drink " +
                        "WHERE o.table.id = :tableId"
        )
})

@Entity
@Table(name = "table_order")
public class TableOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "order_no")
    private Integer orderNo;

    @Column(name = "note", length = 255)
    private String note;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private RestaurantTable table;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderCarteDish> dishes = new ArrayList<>();

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderDrink> drinks = new ArrayList<>();

    @PrePersist
    protected void onCreate() { //Set time on creation
        this.createdAt = LocalDateTime.now(ZoneId.of("Europe/Stockholm"));
    }

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

    public void addDish(OrderCarteDish dish) {
        dish.setOrder(this);   // link child to parent
        this.dishes.add(dish); // add child to parent’s list
    }
    public void addDrink(OrderDrink drink) {
        drink.setOrder(this);   // link child to parent
        this.drinks.add(drink); // add child to parent’s list
    }

    public List<OrderCarteDish> getDishes() { return dishes; }
    public void setDishes(List<OrderCarteDish> dishes) { this.dishes = dishes; }

    public List<OrderDrink> getDrinks() { return drinks; }
    public void setDrinks(List<OrderDrink> drinks) { this.drinks = drinks; }
}