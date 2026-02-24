/**
 * Sitting.java
 *
 * JPA entity representing a table sitting (reservation time slot) in the restaurant application.
 * Maps to the "sitting" table in the database.
 * Provides fields for:
 *  - id                : Primary key, auto-incremented
 *  - startTime         : Start time of the sitting
 *  - date              : Date of the sitting
 *  - durationMinutes   : Duration of the sitting in minutes
 *
 * Maintains a many-to-one relationship with RestaurantTable,
 * meaning each sitting is associated with exactly one table.
 *
 * Named queries included:
 *  - "Sitting.getAll"              : Retrieve all sittings
 *  - "Sitting.findByDate"          : Retrieve sittings for a specific date
 *  - "Sitting.findByTable"         : Retrieve sittings for a specific table
 *  - "Sitting.findByDateAndTable"  : Retrieve sittings by date and table
 *
 * Example usage:
 *   Sitting sitting = new Sitting(startTime, date, 90, table);
 *   LocalDate sittingDate = sitting.getDate();
 *
 * Author: Axel Friman
 * Date: 2026-02-20
 */
package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@NamedQueries({

        @NamedQuery(
                name = "Sitting.getAll",
                query = "SELECT s FROM Sitting s"
        ),

        @NamedQuery(
                name = "Sitting.findByDate",
                query = "SELECT s FROM Sitting s WHERE s.date = :date"
        ),

        @NamedQuery(
                name = "Sitting.findByTable",
                query = "SELECT s FROM Sitting s WHERE s.restaurantTable.id = :tableId"
        ),

        @NamedQuery(
                name = "Sitting.findByDateAndTable",
                query = "SELECT s FROM Sitting s WHERE s.date = :date AND s.restaurantTable.id = :tableId"
        )

})

@Entity
@Table(name = "sitting")
public class Sitting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "duration_minutes", nullable = false)
    private int durationMinutes;

    @ManyToOne
    @JoinColumn(name = "restaurant_table_id", nullable = false)
    private RestaurantTable restaurantTable;

    // =====================
    // Constructors
    // =====================

    public Sitting() {
    }

    public Sitting(LocalTime startTime, LocalDate date, int durationMinutes, RestaurantTable restaurantTable) {
        this.startTime = startTime;
        this.date = date;
        this.durationMinutes = durationMinutes;
        this.restaurantTable = restaurantTable;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public RestaurantTable getRestaurantTable() {
        return restaurantTable;
    }

    public void setRestaurantTable(RestaurantTable restaurantTable) {
        this.restaurantTable = restaurantTable;
    }
}