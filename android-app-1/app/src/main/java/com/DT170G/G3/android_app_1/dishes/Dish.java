package com.DT170G.G3.android_app_1.dishes;


public class Dish {
    public Category category;
    public String description;
    public int id;
    public String name;
    public double price;

    public String getName() {
        return name;
    }
    public int getDishCategoryId() {
        int catId = category.getCategoryId();
        return catId;
    }
}