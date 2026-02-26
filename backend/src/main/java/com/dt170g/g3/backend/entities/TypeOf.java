package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "type_of")
public class TypeOf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    // Constructors
    public TypeOf(){}

    public TypeOf(String name) {
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
