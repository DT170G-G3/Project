/*
 * Java Application project.
 */
package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@NamedQueries(
        @NamedQuery(name = "Lunch.getDishes", query = "SELECT name FROM LunchDish name")
)
/*
 * JPA Entity class, Lunch_Menu
 * Corresponds to a table in the database and makes a Java object out of it.
 * Each of the variables represents a column in the table.
 */
@Entity
@Table(name="lunch_menu")
public class LunchMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;

    /*
     * Makes a join with the dish_lunch_menu,
     * In other words, takes out all the dishes that are on the menu
     * and puts them in a List variable.
     */
    @ManyToMany
    @JoinTable(
            name = "dish_lunch_menu",
            joinColumns = @JoinColumn(name = "lunch_menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<LunchDish> dishes; //This contains all the dishes for that day!

    public List<LunchDish> getDishes(){
        return dishes;
    }

    public DayOfWeek getDay() {return date.getDayOfWeek();}

    public void setDate(LocalDate date){
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDishes(List<LunchDish> dishes) {
        this.dishes.clear();
        if (dishes != null) {
            this.dishes.addAll(dishes);
        }
    }

}
