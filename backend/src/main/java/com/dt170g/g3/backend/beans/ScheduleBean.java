package com.dt170g.g3.backend.beans;

import com.dt170g.g3.backend.entities.Employee;
import com.dt170g.g3.backend.entities.Shift;
import com.dt170g.g3.backend.services.ShiftService;
import jakarta.enterprise.context.RequestScoped;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Named("schedule")
@RequestScoped
public class ScheduleBean {
    @Inject
    private ShiftService shiftService;

    public List<Shift> getShifts(LocalDate date){
        return shiftService.getShiftsByDate(date);
    }

    public Set<Employee> getEmployeesShift(Shift shift){
        return shiftService.getEmployeesByShift(shift);
    }

}
