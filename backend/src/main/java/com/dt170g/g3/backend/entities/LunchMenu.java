/*
 * Java Application project.
 */
package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;
import jdk.jfr.Name;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@NamedQueries({
        @NamedQuery(name = "Lunch.getDishes", query = "SELECT d.name FROM LunchDish d"),

        @NamedQuery(name = "Lunch.getLunchDishesToday",
                    query = "SELECT menu FROM LunchMenu menu WHERE menu.date = :today"),

        @NamedQuery(name = "Lunch.getWeeklyMenues",
                    query = "SELECT menu FROM LunchMenu menu  WHERE menu.date BETWEEN :start AND :end " +
                    "ORDER BY menu.date"),

        @NamedQuery(name= "Lunch.getLunchByDate",
                    query= "SELECT menu FROM LunchMenu menu WHERE menu.date = :targetDate")

})
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
    private List<LunchDish> dishes = new ArrayList<>(); //This contains all the dishes for that day!


    public LunchMenu(){}
    public LunchMenu(LocalDate date){
        this.date = date;
    }

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

    public int getId() {return id;}

    public void setDishes(List<LunchDish> dishes) {
        this.dishes.clear();
        if (dishes != null) {
            this.dishes.addAll(dishes);
        }
    }

    public void addDish(LunchDish dish){
        this.dishes.add(dish);

    }

}
