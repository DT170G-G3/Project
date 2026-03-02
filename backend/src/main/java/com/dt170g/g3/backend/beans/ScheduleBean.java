package com.dt170g.g3.backend.beans;

import com.dt170g.g3.backend.entities.Employee;
import com.dt170g.g3.backend.entities.Shift;
import com.dt170g.g3.backend.services.ShiftService;
import jakarta.enterprise.context.RequestScoped;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Named("schedule")
@ViewScoped
public class ScheduleBean implements Serializable {
    @Inject
    private ShiftService shiftService;

    public List<Shift> getShifts(LocalDate date){
        return shiftService.getShiftsByDate(date);
    }

    public Set<Employee> getEmployeesShift(Shift shift){
        return shiftService.getEmployeesByShift(shift);
    }

    public void addEmployeeToShift(int empId, int shiftId){
        shiftService.assignEmployeeToShift(empId,shiftId);
    }
    public void removeEmployeeFromShift(int empId, int shiftId){
        shiftService.removeEmployeeFromShift(empId,shiftId);
    }

}
