package se.miun.g3.android_app_2.orders;

import se.miun.g3.android_app_2.dishes.Dish;
import se.miun.g3.android_app_2.drinks.Drink;
import se.miun.g3.android_app_2.tables.Table;

import java.util.List;
import java.time.LocalDateTime;


public class Order {
    public int id;
    public String createdAt;
    public int orderNo;
    public String note;
    public Table table;
    public List<Dish> dishes; //carteDish
    public List<Drink> drinks;
}