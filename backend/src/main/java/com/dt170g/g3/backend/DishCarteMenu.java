package com.dt170g.g3.backend;

import com.dt170g.g3.backend.entities.LunchDish;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.List;

public class DishCarteMenu {

    @ManyToMany
    @JoinTable(
            name = "dish_carte_menu",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "carte_menu_id"))
    private List<LunchDish> dish;

    public List<LunchDish> getDishes(){
        return dish;
    }





}

//CREATE TABLE dish_carte_menu(
//        dish_id INT NOT NULL ,
//        carte_menu_id INT NOT NULL ,
//        PRIMARY KEY (dish_id, carte_menu_id) ,
//FOREIGN KEY (dish_id) REFERENCES dish(id) ON DELETE CASCADE ,
//FOREIGN KEY (carte_menu_id) REFERENCES carte_menu(id) ON DELETE CASCADE
//) ENGINE=InnoDB;
