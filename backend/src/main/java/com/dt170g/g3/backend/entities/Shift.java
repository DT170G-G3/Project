package com.dt170g.g3.backend.entities;

import jakarta.ejb.Local;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name= "Shift.getAllShifts",
                query= "SELECT shift FROM Shift shift"),

        @NamedQuery(name= "Shift.getShiftByDate",
                query= "SELECT shift FROM Shift shift WHERE shift.date = :targetDate")
})

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

    public ShiftType getShiftType(){
        return type;
    }

    public Set<Employee> getEmployeeList(){
        return employeeList;
    }

    public int getId(){
        return id;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setType(ShiftType shiftType){
        this.type = shiftType;
    }
}
