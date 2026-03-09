package com.dt170g.g3.backend.beans;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("adminSchedule")
@ViewScoped
public class AdminScheduleBean extends ScheduleBean implements Serializable {
    private int selectedEmpId;
    private int selectedShiftId;

    public int getSelectedEmpId() { return selectedEmpId; }
    public void setSelectedEmpId(int id) { this.selectedEmpId = id; }
    public int getSelectedShiftId() { return selectedShiftId; }
    public void setSelectedShiftId(int id) { this.selectedShiftId = id; }
}