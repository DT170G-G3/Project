

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


    private CarteDish dish = new CarteDish();
    private int selectedCategoryId;
    private int selectedFoodTypeId;
    private List<Category> categories;
    private List<FoodType> foodTypes;






    @PostConstruct
    public void init(){
        this.categories = categoryService.findAllCategories();
        this.foodTypes = foodTypeService.findAllFoodTypes();

    }

    public CarteDish getDish() {
        return dish;
    }


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

        if(carteDishService.dishExistsInDatabase(dish.getName())){
            CarteDish existingDish = carteDishService.getDishByName(dish.getName());
            //Update existing dish
            existingDish.setDescription(dish.getDescription());
            existingDish.setPrice(dish.getPrice());
            existingDish.setCategory(category);
            existingDish.setFoodType(foodType);

            carteMenuService.updateDish(existingDish);
            carteMenuService.addDishToMenu(existingDish);
        }
        else{
            carteDishService.saveToDatabase(dish);
            carteMenuService.addDishToMenu(dish); // adding the new dish to the menu
        }

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
