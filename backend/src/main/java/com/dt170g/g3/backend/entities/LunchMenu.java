package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name="lunch_menu")
public class LunchMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date ;



}
