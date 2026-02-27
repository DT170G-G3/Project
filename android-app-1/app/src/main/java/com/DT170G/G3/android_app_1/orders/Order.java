package com.DT170G.G3.android_app_1.orders;

import com.DT170G.G3.android_app_1.dishes.Dish;
import com.DT170G.G3.android_app_1.drinks.Drink;
import java.util.List;

public class Order {
    public int id;
    public String createdAt;
    public int orderNo;
    public String note;
    public Sitting sitting;
    public List<Dish> dishes; //carteDish
    public List<Drink> drinks;
}

