package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@NamedQueries(
        @NamedQuery(name = "Lunch.getDishes", query = "SELECT name FROM Dish name")
)
@Entity
@Table(name="lunch_menu")
public class LunchMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;


    @ManyToMany
    @JoinTable(
            name = "dish_lunch_menu",
            joinColumns = @JoinColumn(name = "lunch_menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> dishes = new ArrayList<>();

    public List<Dish> getDishes(){
        return dishes;
    }

    public DayOfWeek getDay() {return date.getDayOfWeek();}

    public void setDate(LocalDate date){
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDishes(List<Dish> dishes){
        this.dishes.clear();
        if (dishes != null) {
            this.dishes.addAll(dishes);
        }
    }

}
