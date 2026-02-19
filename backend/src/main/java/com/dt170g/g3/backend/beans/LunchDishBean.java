package com.dt170g.g3.backend.beans;

import com.dt170g.g3.backend.services.LunchDishService;
import com.dt170g.g3.backend.entities.LunchDish;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("dish")
@ViewScoped
public class LunchDishBean implements Serializable {

    @Inject
    private LunchDishService dishService;

    private LunchDish newDish = new LunchDish();

    public LunchDish getNewDish() {
        return newDish;
    }

    public List<LunchDish> getDishes() {
        return dishService.findAllLunchDishes();
    }

    public void addDish() {
        dishService.saveDish(newDish);
        newDish = new LunchDish();
    }
}
