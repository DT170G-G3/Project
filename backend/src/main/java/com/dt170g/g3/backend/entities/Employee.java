package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;
@NamedQueries({
        @NamedQuery(
                name = "Employee.getAll",
                query = "SELECT e FROM Employee e"
        ),
        @NamedQuery(
                name = "Employee.findById",
                query = "SELECT e FROM Employee e WHERE e.id = :id"
        )
})
@Entity
@Table(name ="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    public String getName() {return name;}
    public int getId(){return id;}
}
