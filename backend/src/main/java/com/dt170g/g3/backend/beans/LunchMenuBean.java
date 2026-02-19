package com.dt170g.g3.backend.beans;

import com.dt170g.g3.backend.entities.LunchDish;
import com.dt170g.g3.backend.entities.LunchMenu;
import com.dt170g.g3.backend.services.LunchMenuService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named("lunch")
@ApplicationScoped
public class LunchMenuBean {

    @Inject
    private LunchMenuService lunchMenuService;
    private String selectedDate;
    private List<Integer> selectedDishIds = new ArrayList<>();

    /* ---------- Read ---------- */

    public List<LunchDish> getLunchDishesToday() {
        return lunchMenuService.getLunchDishesToday();
    }

    public List<LunchMenu> getMenuWeek() {
        return lunchMenuService.getMenuWeek();
    }

    public List<LunchDish> getLunchDishesByDate(LocalDate date) {
        return lunchMenuService.getLunchDishesByDate(date);
    }

    /* ---------- Getters / setters ---------- */

    public List<Integer> getSelectedDishIds() {
        return selectedDishIds;
    }

    public void setSelectedDishIds(List<Integer> selectedDishIds) {
        this.selectedDishIds = selectedDishIds;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public void saveToMenu(String localDate){
        LocalDate date = LocalDate.parse(localDate);
        if(!lunchMenuService.menuExistsForDate(date)){
            lunchMenuService.createLunchMenu(date);
        }
    }




}
