package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.entities.Employee;
import com.dt170g.g3.backend.entities.Shift;
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

    public List<Shift> getShiftsByDate(LocalDate date){
        TypedQuery<Shift> messageQuery = entityManager.createNamedQuery("Shift.getShiftByDate", Shift.class)
                .setParameter("targetDate",date);
        return messageQuery.getResultList();
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
