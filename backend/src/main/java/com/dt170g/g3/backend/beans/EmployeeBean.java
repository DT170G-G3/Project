package com.dt170g.g3.backend.beans;

import com.dt170g.g3.backend.entities.Employee;
import com.dt170g.g3.backend.services.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
@Named("emp")
@ViewScoped
public class EmployeeBean implements Serializable {
    @Inject
    private EmployeeService empService;

    private List<Employee> employees;

    @PostConstruct
    public void init(){
        this.employees = empService.getAllEmployees();        ;
    }

    public List<Employee> getEmployees(){
        return employees;
    }
}
