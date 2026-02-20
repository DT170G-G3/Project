/**
 * RestaurantTable.java
 *
 * Entity representing a restaurant table.
 * Maps to the `restaurant_table` table in the database.
 * Provides basic properties:
 *  - id       : Primary key, auto-generated
 *  - seats    : Number of seats at the table
 *  - table_no : Table number in the restaurant
 *
 * Includes JPA NamedQueries for common operations:
 *  - restaurant_table.getAll      : Retrieve all tables
 *  - restaurant_table.findBySeats : Retrieve tables by number of seats
 *
 * Author: Axel Friman
 * Date: 2026-02-20
 */
package com.dt170g.g3.backend.entities;
import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "restaurantTable.getAll",
                query = "SELECT rt FROM RestaurantTable rt"
        ),
        @NamedQuery(
                name = "restaurantTable.findBySeats",
                query = "SELECT rt FROM RestaurantTable rt WHERE rt.seats = :seats"
        )
})

@Entity
@Table(name = "restaurant_table")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int seats;
    private int table_no;

    public RestaurantTable() {
        // No-arg constructor required by JPA
    }

    public RestaurantTable(int seats, int table_no) {
        this.seats = seats;
        this.table_no = table_no;
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

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getTableNum() {
        return table_no;
    }

    public void setTableNum(int table_no) {
        this.table_no = table_no;
    }

}