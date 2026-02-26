package se.miun.g3.android_app_2.orders;

import se.miun.g3.android_app_2.dishes.Dish;
import se.miun.g3.android_app_2.drinks.Drink;
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