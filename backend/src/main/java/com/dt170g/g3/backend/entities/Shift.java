package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shift")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="type_id")
    private ShiftType type;

    @ManyToMany
    @JoinTable(
            name = "employee_works_shift",
            joinColumns = @JoinColumn(name = "shift_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> employeeList = new HashSet<>(); //The employees working that shift.
}
