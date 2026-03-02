package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "FoodType.getAll",
                query = "SELECT fType FROM FoodType fType"
        )
})

@Entity
@Table(name = "food_type")
public class FoodType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    // Constructors
    public FoodType(){}

    public FoodType(String name) {
        this.name = name;
    }

    //Getters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
