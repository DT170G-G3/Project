package com.dt170g.g3.backend.beans;

import com.dt170g.g3.backend.entities.CarteDish;
import com.dt170g.g3.backend.services.CarteMenuService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@ViewScoped
@Named("carteMenu")
public class CarteMenuBean implements Serializable {
    @Inject
    CarteMenuService carteMenuService;

    private List<CarteDish> dishes;
    private CarteDish dish = new CarteDish();

    @PostConstruct
    public void init(){
        this.dishes = carteMenuService.getMenuDishes(); // getMenuDishes return all dishes on the many in specific order
    }

    //Getters and setters
    public List<CarteDish> getDishes() {
        return dishes;
    }

    public void setDishes(List<CarteDish> dishes) {
        this.dishes = dishes;
    }


    /**
     * Groups dishes by their category name and type of food.
     * This method creates a LinkedHashMap where each key represents
     * a category and the value represents a LinkedHashMap with a key of type of
     * food name and a value of a List of CarteDishes.
     *
     * @return a LinkedHashMap grouping dishes first by category,
     *         then by type, preserving insertion order.
     */
    public LinkedHashMap<String, LinkedHashMap<String, List<CarteDish>>> getGroupedDishes(){
        LinkedHashMap<String, LinkedHashMap<String, List<CarteDish>>> menu = new LinkedHashMap<>();

        for(CarteDish dish : dishes){
            String categoryName = dish.getCategory().getName();
            if(!menu.containsKey(categoryName)){
                menu.put(categoryName, new LinkedHashMap<>());
            }

            LinkedHashMap<String, List<CarteDish>> typeMap = menu.get(categoryName); // reference to value of menu

            String typeName;
            if(dish.getFoodType() != null){ // Only mains have types
                typeName = dish.getFoodType().getName();
            } else{
                typeName = "";
            }

            if(!typeMap.containsKey(typeName)){
                typeMap.put(typeName, new ArrayList<>());
            }


            typeMap.get(typeName).add(dish); // add dish to the value of the value of menu

        }
        return menu;
    }






    public void removeDishfromMenu(int id){
        carteMenuService.removeDishfromMenu(id);
    }
}
