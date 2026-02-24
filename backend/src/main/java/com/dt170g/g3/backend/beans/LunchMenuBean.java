package com.dt170g.g3.backend.beans;

import com.dt170g.g3.backend.entities.LunchDish;
import com.dt170g.g3.backend.entities.LunchMenu;
import com.dt170g.g3.backend.services.LunchDishService;
import com.dt170g.g3.backend.services.LunchMenuService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named("lunch")
@ViewScoped
public class LunchMenuBean implements Serializable {

    @Inject
    private LunchMenuService lunchMenuService;
    @Inject
    private LunchDishService lunchDishService;
    @Inject
    private LunchDishBean dishBean;
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

    /* It works, but not very refined! Might need to be broken down for parts.
    * "Finns inget skrot, bara gamla grejer som går att använda på nya sätt"
    * -Mulle Meck
    *  */


        @Transactional
        public void saveToMenu(String localDate){
            LocalDate date = LocalDate.parse(localDate);
            if(!lunchMenuService.menuExistsForDate(date)){
                lunchMenuService.createLunchMenu(date);
            }
            LunchMenu menu = lunchMenuService.getLunchMenuByDate(date);
            LunchDish dish = dishBean.getNewDish();
            if(!lunchDishService.checkIfDishExist(dish)){
                lunchDishService.saveDish(dish);
            }
            menu.addDish(dish);
    
            dishBean.reset();
        }

}
