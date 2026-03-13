package se.miun.g3.android_app_2.orders;



import se.miun.g3.android_app_2.dishes.Dish;
import se.miun.g3.android_app_2.drinks.Drink;
import se.miun.g3.android_app_2.tables.Table;

import java.util.List;
import java.time.LocalDateTime;


public class Order {
    public String createdAt;
    public List<DishItem> dishes;
    public List<DrinkItem> drinks;
    public int id;
    public String note;
    public int orderNo;
    public Table table;
}