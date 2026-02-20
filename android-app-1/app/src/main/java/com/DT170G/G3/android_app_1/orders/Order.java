package com.DT170G.G3.android_app_1.orders;

import java.util.List;

//Everything set to public until connected to database as refactoring will be necessary anyway
public class Order {
    public String note;
    public int tableId;
    public List<OrderItem> orderedItems;
    public static class OrderItem {
        public int dishId;
        public int category; //temp: 1 for appetizer, 2 for main course, 3 for dessert
    }
}