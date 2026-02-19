package se.miun.g3.android_app_2.orders;

import java.util.List;

//Everything set to public until connected to database as refactoring will be necessary anyway
// NOTE: This App2 Order class has an id field.
//which App1's Order class does not.
public class Order {
    public String note;
    public int tableId;
    public List<OrderItem> orderedItems;
    public static class OrderItem {
        public int dishId;
        public int category; //temp: 1 for appetizer, 2 for main course, 3 for dessert
    }
}