package com.dt170g.g3.backend.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.time.LocalDate;
import java.util.List;

@Named("week")
@ApplicationScoped
public class WeekBean {
    private LocalDate weekStart;
    private List<LocalDate> weekdays;

    public int getCurrentWeekNumber(){
        return 1;
    }

}
