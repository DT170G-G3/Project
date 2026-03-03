package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.entities.Employee;
import com.dt170g.g3.backend.entities.Shift;
import com.dt170g.g3.backend.entities.ShiftType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ShiftService {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public List<Shift> getShiftsByDate(LocalDate date) {
        System.out.println("Running getShiftsByDate!");
        List<Shift> shifts = entityManager.createNamedQuery("Shift.getShiftByDate", Shift.class)
                .setParameter("targetDate", date)
                .getResultList();

        if (shifts.isEmpty()) {
            List<ShiftType> types = entityManager.createQuery(
                    "SELECT st FROM ShiftType st", ShiftType.class).getResultList();

            for (ShiftType type : types) {
                Shift shift = new Shift();
                shift.setDate(date);
                shift.setType(type);
                entityManager.persist(shift);
                shifts.add(shift);
            }
        }

        return shifts;
    }

    public Set<Employee> getEmployeesByShift(Shift shift){
        return shift.getEmployeeList();
    }

    @Transactional
    public void assignEmployeeToShift(int empId, int shiftId){
        Shift shift = entityManager.find(Shift.class, shiftId);
        Employee employee = entityManager.find(Employee.class, empId);

        shift.getEmployeeList().add(employee);
    }

    @Transactional
    public void removeEmployeeFromShift(int empId, int shiftId){
        Shift shift = entityManager.find(Shift.class, shiftId);
        Employee employee = entityManager.find(Employee.class, empId);

        shift.getEmployeeList().remove(employee);
    }
}
