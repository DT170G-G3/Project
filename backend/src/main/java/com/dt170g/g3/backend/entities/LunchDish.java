/*
 * Java Application project.
 * Author: Jesper
 */

package com.dt170g.g3.backend.entities;
/*
 * JPA Entity class, Dish
 * Corresponds to a table in the database and makes a Java object out of it.
 * Each of the variables represents a column in the table.
 */
import jakarta.persistence.*;

@NamedQueries(
        @NamedQuery(name = "LunchDish.getAll", query = "SELECT name FROM LunchDish name")
)

@Entity
@Table(name = "lunch_dish")
public class LunchDish {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private double price;

    public LunchDish(){}
    public LunchDish(String name, String description, double price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

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

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }
}
