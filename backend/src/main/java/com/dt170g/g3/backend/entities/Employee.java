package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;
@NamedQueries({
        @NamedQuery(
                name = "Employee.getAll",
                query = "SELECT d FROM Employee d"
        ),
        @NamedQuery(
                name = "Employee.findById",
                query = "SELECT d FROM Drink d WHERE d.id = :id"
        )
})
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    public String getName() {return name;}
}
