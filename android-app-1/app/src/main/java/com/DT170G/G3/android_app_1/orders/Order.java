package com.DT170G.G3.android_app_1.orders;

import com.DT170G.G3.android_app_1.dishes.Dish;
import com.DT170G.G3.android_app_1.drinks.Drink;
import java.util.List;
import java.time.LocalDateTime;


public class Order {
    public String createdAt;
    public List<Dish> dishes;
    public List<Drink> drinks;
    public int id;
    public int orderNo;
    public Sitting sitting;
}