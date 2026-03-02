

package com.dt170g.g3.backend.beans;


import com.dt170g.g3.backend.entities.CarteDish;
import com.dt170g.g3.backend.entities.Category;
import com.dt170g.g3.backend.entities.FoodType;
import com.dt170g.g3.backend.services.CarteDishService;
import com.dt170g.g3.backend.services.CarteMenuService;
import com.dt170g.g3.backend.services.CategoryService;
import com.dt170g.g3.backend.services.FoodTypeService;
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
    private CarteDishService carteDishService;

    @Inject
    private CarteMenuService carteMenuService;

    @Inject
    private CategoryService categoryService;

    @Inject
    private FoodTypeService foodTypeService;

    private List<CarteDish> dishes;
    private CarteDish dish = new CarteDish();
    private int selectedCategoryId;
    private int selectedFoodTypeId;
    private List<Category> categories;
    private List<FoodType> foodTypes;
    //private List<CarteDish> dishes;





    @PostConstruct
    public void init(){
        //this.dishes = carteDishService.getMenuDishes(); // getMenuDishes return all dishes on the many in specific order
        this.categories = categoryService.findAllCategories();
        this.foodTypes = foodTypeService.findAllFoodTypes();

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
//    public LinkedHashMap<String, LinkedHashMap<String, List<CarteDish>>> getGroupedDishes(){
//        LinkedHashMap<String, LinkedHashMap<String, List<CarteDish>>> menu = new LinkedHashMap<>();
//
//        for(CarteDish dish : dishes){
//            String categoryName = dish.getCategory().getName();
//            if(!menu.containsKey(categoryName)){
//                menu.put(categoryName, new LinkedHashMap<>());
//            }
//
//            LinkedHashMap<String, List<CarteDish>> typeMap = menu.get(categoryName); // reference to value of menu
//
//            String typeName;
//            if(dish.getFoodType() != null){ // Only mains have types
//                typeName = dish.getFoodType().getName();
//            } else{
//                typeName = "";
//            }
//
//            if(!typeMap.containsKey(typeName)){
//                typeMap.put(typeName, new ArrayList<>());
//            }
//
//
//            typeMap.get(typeName).add(dish); // add dish to the value of the value of menu
//
//        }
//        return menu;
//    }

//    public void saveToMenu(){
//
//        carteDishService.saveToMenu(dish);
//    }


    /**
     * Saves the current dish to the menu.
     *
     * The method retrieves the selected Category and FoodType based on their IDs,
     * assigns them to the dish, then saves the dish to the database.
     * Finally, the dish is added to the current menu.
     */
    public void saveDishToMenu(){
        Category category = categoryService.findById(selectedCategoryId); // find selected category
        dish.setCategory(category);

        FoodType foodType = foodTypeService.findById(selectedFoodTypeId); // find selected food type
        dish.setFoodType(foodType);

        carteDishService.saveToDatabase(dish); // saving new dish to database
        carteMenuService.addDishToMenu(dish); // adding the new dish to the menu

    }

    //Getters and setters

    public void setSelectedCategoryId(int selectedCategoryId){
        this.selectedCategoryId = selectedCategoryId;
    }

    public int getSelectedCategoryId(){
        return selectedCategoryId;
    }



    public void setSelectedFoodTypeId(int selectedFoodTypeId){
        this.selectedFoodTypeId = selectedFoodTypeId;
    }

    public int getSelectedFoodTypeId(){
        return selectedFoodTypeId;
    }


    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<FoodType> getFoodTypes() {
        return foodTypes;
    }


    public void setFoodTypes(List<FoodType> foodTypes) {
        this.foodTypes = foodTypes;
    }


}
