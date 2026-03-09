package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.entities.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class EmployeeService {
    @PersistenceContext
    EntityManager entityManager;

    public List<Employee> getAllEmployees(){
        TypedQuery<Employee> messageQuery = entityManager.createNamedQuery("Employee.getAll", Employee.class);
        return messageQuery.getResultList();
    }

    public Employee getEmployeeFromAndroidId(String androidId){
        TypedQuery<Employee> messageQuery = entityManager.createNamedQuery("Employee.getByAndroidId", Employee.class)
                .setParameter("androidId", androidId);;
        return messageQuery.getSingleResult();
    }
}
