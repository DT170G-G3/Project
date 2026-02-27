package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "shift_type")
public class ShiftType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalTime start_time;
    private LocalTime end_time;
    public String getName(){
        return this.name;
    }

}
