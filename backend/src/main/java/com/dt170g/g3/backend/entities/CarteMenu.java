package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "carte_menu")
public class CarteMenu {

    //Dish dish = new Dish();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;


    @ManyToMany
    @JoinTable(
            name = "dish_carte_menu",
            joinColumns = @JoinColumn(name = "carte_menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<LunchDish> dishes;


    public List<LunchDish> getDishes() {
        return dishes;
    }

    public Long getId(){
        return id;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }











}
//
//CREATE TABLE carte_menu(
//        id INT AUTO_INCREMENT PRIMARY KEY,
//        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
//) ENGINE=InnoDB;
