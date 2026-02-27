

package com.dt170g.g3.backend.beans;


import com.dt170g.g3.backend.entities.CarteDish;
import com.dt170g.g3.backend.entities.Category;
import com.dt170g.g3.backend.services.CarteDishService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Backing bean responsible for managing and presenting menu dishes
 *  which is used by the xhtml
 *
 * <p>This bean retrieves {@link CarteDish} entities from
 * {@link CarteDishService}, stores them for the current view,
 * and provides grouped access to the dishes by category.</p>
 *
 * <p>The bean is {@code @ViewScoped}, meaning its state is preserved
 * for the duration of the current JSF view.</p>
 *
 *
 */
@ViewScoped
@Named("carteDish")
public class CarteDishBean implements Serializable {

    @Inject
    CarteDishService carteDishService;

    private List<CarteDish> dishes;
    private CarteDish dish = new CarteDish();




    @PostConstruct
    public void init(){
        this.dishes = carteDishService.getMenuDishes(); // getMenuDishes return all dishes on the many in specific order
    }

    public CarteDish getDish() {
        return dish;
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
            if(dish.getTypeGroup() != null){ // Only mains have types
                typeName = dish.getTypeGroup().getName();
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

    public void saveToMenu(){

        carteDishService.saveToMenu(dish);
    }




}
