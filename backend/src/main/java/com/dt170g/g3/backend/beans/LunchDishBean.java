package com.dt170g.g3.backend.beans;

import com.dt170g.g3.backend.services.LunchDishService;
import com.dt170g.g3.backend.entities.LunchDish;
import jakarta.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("dish")
@ViewScoped
public class LunchDishBean implements Serializable{

    @Inject
    private LunchDishService dishService;

    private LunchDish newDish = new LunchDish();

    public LunchDish getNewDish() {
        return newDish;
    }

    public List<LunchDish> getDishes() {
        return dishService.findAllLunchDishes();
    }

    public void reset() {
        newDish = new LunchDish();
    }

    //Autocompletion using PrimeFaces for JSF
    public List<String> completeDish(String query) {
        return dishService.searchByName(query)
                .stream()
                .map(LunchDish::getName)
                .toList();
    }

    public void onDishSelect(SelectEvent<String> event) {
        String selectedName = event.getObject();

        // Logik för att hitta rätten i din databas/lista
        LunchDish results = dishService.searchByName(selectedName);

        if (results != null && !results.isEmpty()) {
            LunchDish dish = results.get(0);
            this.newDish.setDescription(dish.getDescription());
            this.newDish.setPrice(dish.getPrice());
            this.newDish.setName(dish.getName());
        }
    }
}
